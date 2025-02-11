
// This is a workaround for the required property in com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
project.setProperty("mainClassName", "AggregateSnippetsKt")

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
    implementation("io.github.cdimascio:dotenv-kotlin:6.+")
}

kotlin {
    jvmToolchain(17)
}
