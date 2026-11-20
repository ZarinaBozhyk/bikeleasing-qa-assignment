plugins {
    kotlin("jvm") version "2.2.21"
    id("net.serenity-bdd.serenity-gradle-plugin") version "5.4.2"
}

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

    testImplementation(platform("com.fasterxml.jackson:jackson-bom:2.21.8"))
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    testImplementation(kotlin("reflect"))

    testRuntimeOnly("org.slf4j:slf4j-simple:2.0.17")
}

kotlin {
    jvmToolchain(17)
}

val forwardedPropertyPrefixes = listOf("headless.", "webdriver.", "serenity.", "api.", "environment")

tasks.test {
    useJUnitPlatform()
    systemProperties(
        System.getProperties()
            .mapKeys { it.key.toString() }
            .filterKeys { key -> forwardedPropertyPrefixes.any { key.startsWith(it) } }
    )

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