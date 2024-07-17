plugins {
    kotlin("multiplatform") version "2.0.0"
    `maven-publish`
}

group = "io.github.myshkouski"
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
            implementation(libs.kotlin.wrappers.typescript)
            implementation(libs.kotlinx.coroutines.core)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.kotlinx.coroutines.test)
            implementation(kotlin("reflect"))
        }
    }
}
