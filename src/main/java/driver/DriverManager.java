package driver;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverManager {
    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static DriverManager instance;
    private WebDriver driver;

    private DriverManager() {}

    /**
     * Get instance of DriverManager(Singleton)
     * @return DriveManager instance
     */
    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
            logger.info("DriverManager created");
        }
        return instance;
    }

    /**
     * WebDriver intance setter
     * @param driver WebDriver
     */
    public void setDriver(WebDriver driver) {
        this.driver = driver;
        logger.info("WebDriver set");
    }

    /**
     * Get WebDriver instance
     * @return WebDriver instance
     * @throws RuntimeException if driver is not initialized
     */
    public WebDriver getDriver() {
        if (driver == null) {
            logger.error("Driver not initialized");
            throw new RuntimeException("WebDriver not initialized");
        }
        return driver;
    }

    /**
     * Close WebDriver and clear the instance reference
     */
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("Driver quit");
        }
    }
}
