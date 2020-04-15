import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
//    id("kotlinx-serialization")
}

kotlin {
    //select iOS target platform depending on the Xcode environment variables
    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iOSTarget("ios") {
        binaries {
            framework {
                baseName = "common"
            }
        }
    }

    jvm("android")

    val ktorVersion = "1.2.6"

    sourceSets["commonMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.3")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:0.14.0")
        implementation("io.ktor:ktor-client-core:$ktorVersion")
        implementation("io.ktor:ktor-client-json:$ktorVersion")
        implementation("io.ktor:ktor-client-serialization:$ktorVersion")
        //implementation("io.ktor:ktor-client-ios:$ktorVersion")
    }

    sourceSets["androidMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:0.14.0")
        implementation("io.ktor:ktor-client-android:$ktorVersion")
    }

    sourceSets["iosMain"].dependencies {
        implementation("io.ktor:ktor-client-ios:$ktorVersion")
    }
}

val packForXcode by tasks.creating(Sync::class) {
    val targetDir = File(buildDir, "xcode-frameworks")

    /// selecting the right configuration for the iOS
    /// framework depending on the environment
    /// variables set by Xcode build
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val framework = kotlin.targets
        .getByName<KotlinNativeTarget>("ios")
        .binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)

    from({ framework.outputDirectory })
    into(targetDir)

    /// generate a helpful ./gradlew wrapper with embedded Java path
    doLast {
        val gradlew = File(targetDir, "gradlew")
        gradlew.writeText("#!/bin/bash\n"
                + "export 'JAVA_HOME=${System.getProperty("java.home")}'\n"
                + "cd '${rootProject.rootDir}'\n"
                + "./gradlew \$@\n")
        gradlew.setExecutable(true)
    }
}

tasks.getByName("build").dependsOn(packForXcode)