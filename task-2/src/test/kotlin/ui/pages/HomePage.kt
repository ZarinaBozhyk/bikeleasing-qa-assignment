package ui.pages

import net.serenitybdd.core.pages.PageObject
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable
import org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated
import org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated
import java.net.URI

class HomePage : PageObject() {

    // Locators.........................................................................................................

    private val continueWithoutConsentButton =
        By.xpath("//button[@data-testid='actionButton-reject']")

    private val dropdownLinks =
        By.xpath("./li/a")

    private fun menuButton(menuName: String): By =
        By.xpath(
            "//ul[contains(@class, 'comp-main-nav--desktop')]" +
                    "/li/button[contains(normalize-space(.), '$menuName')]"
        )

    private fun menuDropdown(menuName: String): By {
        val button = driver.findElement(menuButton(menuName))

        val dropdownId = checkNotNull(
            button.getDomAttribute("aria-controls")
        ) {
            "Menu '$menuName' has no aria-controls attribute"
        }

        return By.id(dropdownId)
    }

    // Actions..........................................................................................................

    fun continueWithoutConsent() {
        waitFor(
            elementToBeClickable(continueWithoutConsentButton)
        ).click()

        // Wait until the banner is gone, so it does not cover other elements
        waitFor(
            invisibilityOfElementLocated(continueWithoutConsentButton)
        )
    }

    fun openMenu(menuName: String) {
        val button = waitFor(elementToBeClickable(menuButton(menuName)))

        if (button.getDomAttribute("aria-expanded") != "true") {
            button.click()
        }

        waitFor(visibilityOfElementLocated(menuDropdown(menuName)))
    }

    // Queries..........................................................................................................

    fun currentUrl(): String {
        val actualUrl = driver.currentUrl

        return checkNotNull(actualUrl) {
            "The browser did not return a current URL."
        }
    }

    fun currentOrigin(): String {
        val uri = URI(currentUrl())
        return "${uri.scheme}://${uri.host}"
    }

    fun menuItems(menuName: String): List<String> {
        val dropdown = waitFor(
            visibilityOfElementLocated(menuDropdown(menuName))
        )

        return dropdown.findElements(dropdownLinks)
            .filter { it.isDisplayed }
            .map { it.text.trim() }
    }
}