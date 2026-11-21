package config

import net.serenitybdd.core.di.SerenityInfrastructure
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration

object TestConfig {

    // Serenity configuration: serenity.conf plus system properties
    private val configuration by lazy {
        EnvironmentSpecificConfiguration.from(
            SerenityInfrastructure.getEnvironmentVariables()
        )
    }

    val apiBaseUrl: String
        get() = required("api.base.url")

    private fun required(key: String): String =
        configuration.getOptionalProperty(key).orElse(null)
            ?: error("Missing '$key' in serenity.conf or as -D$key=...")
}