import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20"
}

group = "org.theagilemonkeys"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.softmotions.com/repository/softmotions-public")
}

dependencies {
    implementation("io.horizen:sidechains-sdk:0.3.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.11"
}