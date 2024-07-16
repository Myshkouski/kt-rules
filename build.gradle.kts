plugins {
    kotlin("multiplatform") version "2.0.0"
}

group = "dev.myshkouski"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// dependencies {
//     testImplementation(kotlin("test"))
// }

// tasks.test {
//     useJUnitPlatform()
// }

kotlin {
    jvm()
    jvmToolchain(8)

    js {
        nodejs()
        // compilations.all {
        //     compileTaskProvider.configure {
        //         compilerOptions.freeCompilerArgs.add("-Xir-minimized-member-names=false")
        //     }
        // }
        binaries.executable()
        useEsModules()
        generateTypeScriptDefinitions()
    }

    linuxX64()
    linuxArm64()
    macosX64()
    macosArm64()
    iosArm64()
    watchosArm64()
    tvosX64()
    tvosArm64()
    mingwX64()

    sourceSets {
        jsMain.dependencies {
            implementation(kotlinw("typescript", "5.4.5-pre.774"))
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0-RC")
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0-RC")
            implementation(kotlin("reflect"))
        }
    }
}

private fun kotlinw(target: String, version: String): String {
    return "org.jetbrains.kotlin-wrappers:kotlin-$target:$version"
}
