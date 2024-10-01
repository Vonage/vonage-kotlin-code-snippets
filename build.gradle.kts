plugins {
    kotlin("jvm") version "2.0.20"
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("com.vonage:server-sdk-kotlin:1.0.0-RC2")
}

kotlin {
    jvmToolchain(17)
}
