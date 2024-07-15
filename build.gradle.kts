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
    jvmToolchain(21)

    sourceSets {
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0-RC")
            implementation(kotlin("reflect"))
        }
    }
}
