plugins {
    kotlin("jvm") version "2.0.20"
    id("io.ktor.plugin") version "2.3.12"
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("com.vonage:server-sdk-kotlin:1.1.0")
    implementation("io.ktor:ktor-server-netty")
    implementation("io.ktor:ktor-serialization-jackson")
}

kotlin {
    jvmToolchain(17)
}
