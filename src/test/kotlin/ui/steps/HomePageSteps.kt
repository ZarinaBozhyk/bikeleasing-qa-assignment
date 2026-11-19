package ui.steps

import net.serenitybdd.annotations.Step
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

    open fun currentUrl(): String = homePage.currentUrl()

    @Step("Open the '{0}' navigation menu")
    open fun openMenu(menuName: String) {
        homePage.openMenu(menuName)
    }

    @Step("Read links from the '{0}' navigation menu")
    open fun menuItems(menuName: String): List<String> =
        homePage.menuItems(menuName)
}