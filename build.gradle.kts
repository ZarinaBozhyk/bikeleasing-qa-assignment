plugins {
    kotlin("jvm") version "2.2.21"
    id("net.serenity-bdd.serenity-gradle-plugin") version "5.4.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val serenityVersion = "5.4.2"

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.1.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("net.serenity-bdd:serenity-core:$serenityVersion")
    testImplementation("net.serenity-bdd:serenity-junit5:$serenityVersion")

    testImplementation("net.serenity-bdd:serenity-screenplay:${serenityVersion}")
    testImplementation("net.serenity-bdd:serenity-screenplay-rest:${serenityVersion}")

    testImplementation(platform("com.fasterxml.jackson:jackson-bom:2.21.7"))
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    testImplementation(kotlin("reflect"))

    testRuntimeOnly("org.slf4j:slf4j-simple:2.0.17")

    testImplementation("net.serenity-bdd:serenity-screenplay-webdriver:${serenityVersion}")
}

kotlin {
    jvmToolchain(17)
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
    }

    finalizedBy("aggregate")
    maxParallelForks = 2
}

tasks.named("aggregate") {
    outputs.upToDateWhen { false }
}

tasks.named<Delete>("clean") {
    delete(layout.projectDirectory.dir("target/site/serenity"))
}