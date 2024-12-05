plugins {
    kotlin("jvm") version "2.1.+"
    id("io.ktor.plugin") version "3.0.+"
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("com.vonage:server-sdk-kotlin:1.+")
    implementation("io.ktor:ktor-server-netty")
    implementation("io.ktor:ktor-serialization-jackson")
}

kotlin {
    jvmToolchain(17)
}
