package pages;

import driver.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BasePage {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = DriverManager.getInstance().getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        PageFactory.initElements(driver, this);
        logger.debug("initialized BasePage");
    }

    /**
     * Find element using xPath
     *
     * @param xpath expression
     * @return WebElement
     */
    protected WebElement findElementByXPath(String xpath) {
        logger.trace("Finding element by xpath: {}", xpath);
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    /**
     * click element found by xpath
     *
     * @param xpath expression
     */
    protected void clickElementByXPath(String xpath) {
        logger.trace("Clicking element by xpath: {}", xpath);
        findElementByXPath(xpath).click();
    }

    /**
     * Type text into element found by xpath
     *
     * @param xpath expression
     * @param text  text to type
     */
    protected void typeElementByXPath(String xpath, String text) {
        logger.trace("Typing {} into element by xpath: {}", text, xpath);
        WebElement element = findElementByXPath(xpath);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Clear element found by xpath
     *
     * @param xpath expression
     */
    protected void clearElementByXPath(String xpath) {
        logger.trace("Clearing element by xpath: {}", xpath);
        findElementByXPath(xpath).sendKeys(Keys.CONTROL + "a", Keys.DELETE);
    }

    /**
     * Check if an element is present using his xpath
     *
     * @param xpath expression
     * @return true if element exist else return false.
     */
    protected boolean isElementPresent(String xpath) {
        try {
            driver.findElement(By.xpath(xpath));
            logger.debug("Element is present in xpath: {}", xpath);
            return true;
        } catch (NoSuchElementException e) {
            logger.debug("Element is NOT present in xpath: {}", xpath);
            return false;
        }
    }
}
