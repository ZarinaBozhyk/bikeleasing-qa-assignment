package ui.tests

import net.serenitybdd.annotations.Managed
import net.serenitybdd.annotations.Steps
import net.serenitybdd.junit5.SerenityJUnit5Extension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.openqa.selenium.WebDriver
import ui.steps.HomePageSteps


@ExtendWith(SerenityJUnit5Extension::class)
class HomePageTest {

    @Suppress("unused")
    @Managed
    lateinit var browser: WebDriver

    @Steps
    lateinit var home: HomePageSteps

    @Test
    fun `opens the Bikeleasing homepage at the correct URL`() {
        home.openHomePage()
        home.shouldBeOn(EXPECTED_ORIGIN)
    }

    @ParameterizedTest(name = "{0} menu contains its expected links")
    @MethodSource("menuCases")
    fun `shows the expected navigation links`(
        menuName: String,
        expectedItems: List<String>
    ) {
        home.openHomePage()
        home.continueWithoutConsent()
        home.openMenu(menuName)

        home.shouldShowMenuItems(menuName, expectedItems)
    }

    companion object {

        private const val EXPECTED_ORIGIN = "https://bikeleasing.de"

        @JvmStatic
        fun menuCases(): List<Arguments> = listOf(
            Arguments.of(
                "Arbeitgeber",
                listOf(
                    "Infos für Arbeitgeber",
                    "Kostenlose Beratung buchen",
                    "Probonio-Benefits",
                    "Dienstrad-Leasing für KMU",
                    "Gehaltsextra: Dienstrad",
                    "Versicherungen",
                    "Öffentliche Arbeitgeber"
                )
            ),
            Arguments.of(
                "Selbstständige",
                listOf(
                    "Infos für Selbständige",
                    "Blitzleasing",
                    "Händler finden"
                )
            )
        )
    }
}