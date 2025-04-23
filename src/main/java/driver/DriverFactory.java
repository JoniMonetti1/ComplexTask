package driver;

import constants.AppConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;

public class DriverFactory {
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    /**
     * Create WebDriver instance based on the browser type
     *
     * @param browserTypeEnum BrowserTypeEnum
     * @return WebDriver instance
     */
    public static WebDriver createDriver(BrowserTypeEnum browserTypeEnum) {
        String os = System.getProperty("os.name").toLowerCase();
        String driverExtension = os.contains("win") ? ".exe" : "";
        WebDriver driver;

        switch (browserTypeEnum) {
            case FIREFOX:
                String geckoDriverPath = Paths.get(AppConstants.DRIVER_PATH, "geckodriver" + driverExtension).toString();
                System.setProperty("webdriver.gecko.driver", geckoDriverPath);
                driver = new FirefoxDriver();
                logger.info("Firefox Driver created");
                break;
            case EDGE:
                String edgeDriverPath = Paths.get(AppConstants.DRIVER_PATH, "msedgedriver" + driverExtension).toString();
                System.setProperty("webdriver.edge.driver", edgeDriverPath);
                driver = new EdgeDriver();
                logger.info("Edge Driver created");
                break;
            default:
                logger.error("Unsupported browser type: {}", browserTypeEnum);
                throw new IllegalArgumentException("Unsupported browser type: " + browserTypeEnum);
        }

        driver.manage().window().maximize();
        logger.info("WebDriver created for browser: {}", browserTypeEnum);
        return driver;
    }

    /**
     * Get browser type from system property
     *
     * @return BrowserTypeEnum based on system property
     */
    public static BrowserTypeEnum getBrowserTypeFromSystemProperty() {
        String browserProperty = System.getProperty("browser", "firefox").toUpperCase();
        try {
            return BrowserTypeEnum.valueOf(browserProperty);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid browser type: {}. Using FIREFOX as default.", browserProperty);
            return BrowserTypeEnum.FIREFOX;
        }
    }
}
