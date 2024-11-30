package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * This class contains basic driver interaction with the browser.
 */
public class Actions {

    public WebDriver driver;
    public Long timeout = Long.parseLong(ConfigManager.getProperty("timeout"));
    public String baseUrl = ConfigManager.getProperty("baseUrl");
    public Actions(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Click to a web element.
     *
     * @param locator -   Locator of the element to be clicked.
     */
    public void click(By locator) {
        waitForMilliseconds(timeout);
        waitForElementToBeVisible(locator);
        driver.findElement(locator).click();
    }

    /**
     * Type the given input to a web element.
     *
     * @param locator -   Locator of the element to type to.
     * @param input   -   Text to be typed.
     */
    public void typeKeys(By locator, String input) {
        waitForMilliseconds(timeout);
        driver.findElement(locator).sendKeys(input);
    }

    /**
     * Get the text of a web element.
     *
     * @param locator -   Locator of the element to retrieve the text from.
     * @return -   Text of the element.
     */
    public String getText(By locator) {
        waitForElementToBeVisible(locator);
        return driver.findElement(locator).getText();
    }

    /**
     * Get the text of a web element with multiple occurrences.
     *
     * @param locator -   Locator of the element to retrieve the text from.
     * @param i       -   Number of element to get text from.
     * @return -   Text of the element.
     */
    public String getNthElementText(By locator, int i) {
        return driver.findElements(locator).get(i).getText();
    }

    /**
     * Wait for a certain web element to appear on the page.
     *
     * @param locator -   Locator of the element to wait for.
     */
    public void waitForElementToBeVisible(By locator) {
        waitForMilliseconds(timeout);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Get the times a locator is visible on the page.
     *
     * @param locator -   Locator to search for.
     */
    public int getElementMatchingLocators(By locator) {
        return driver.findElements(locator).size();
    }

    /**
     * Wait to be navigated to a certain page url.
     * Method appends the given text at the end of the application url.
     *
     * @param pageUrl -   Endpoint of the page to wait for.
     */
    public void waitForUrl(String pageUrl) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.urlToBe(baseUrl + "/" + pageUrl));
    }

    /**
     * Implicit wait for X milliseconds.
     *
     * @param milliseconds -   Milliseconds the system will be idle.
     */
    public void waitForMilliseconds(Long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns true if the web element is visible.
     *
     * @param locator -   Locator of the element to check for visibility.
     * @return -   visibility status of the element.
     */
    public boolean isElementVisible(By locator) {
        waitForMilliseconds(timeout);
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}