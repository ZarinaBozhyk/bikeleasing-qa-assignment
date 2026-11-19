package config

import net.serenitybdd.core.di.SerenityInfrastructure
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration

/**
 * Central access point for test settings.
 *
 * Values come from src/test/resources/serenity.conf
 * and can be overridden with -D options on the command line.
 */
object TestConfig {

    // Serenity configuration: serenity.conf plus system properties
    private val configuration by lazy {
        EnvironmentSpecificConfiguration.from(
            SerenityInfrastructure.getEnvironmentVariables()
        )
    }

    // Base URL of the Cat Facts API, e.g. https://catfact.ninja
    val apiBaseUrl: String
        get() = required("api.base.url")

    // Reads a property and fails with a clear message if it is missing
    private fun required(key: String): String =
        configuration.getOptionalProperty(key).orElse(null)
            ?: error("Missing '$key' in serenity.conf or as -D$key=...")
}