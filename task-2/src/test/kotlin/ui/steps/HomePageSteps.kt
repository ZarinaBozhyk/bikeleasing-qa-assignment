package ui.steps

import net.serenitybdd.annotations.Step
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import ui.pages.HomePage

open class HomePageSteps {

    lateinit var homePage: HomePage

    @Step("Open the Bikeleasing homepage")
    open fun openHomePage() {
        homePage.open()
    }

    @Step("Continue without optional cookies")
    open fun continueWithoutConsent() {
        homePage.continueWithoutConsent()
    }

    @Step("Check that the browser is on {0}")
    open fun shouldBeOn(expectedOrigin: String) {
        assertEquals(
            expectedOrigin,
            homePage.currentOrigin(),
            "Unexpected site. Full current URL: ${homePage.currentUrl()}"
        )
    }

    @Step("Open the '{0}' navigation menu")
    open fun openMenu(menuName: String) {
        homePage.openMenu(menuName)
    }

    @Step("Check that the '{0}' menu contains {1}")
    open fun shouldShowMenuItems(menuName: String, expectedItems: List<String>) {
        val actualItems = homePage.menuItems(menuName)

        assertTrue(
            actualItems.containsAll(expectedItems),
            "Menu '$menuName': expected $expectedItems, but found $actualItems"
        )
    }
}