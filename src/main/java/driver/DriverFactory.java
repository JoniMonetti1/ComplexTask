package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverFactory {
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    public static WebDriver createDriver(BrowserTypeEnum browserTypeEnum) {
        WebDriver driver;

        switch (browserTypeEnum) {
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                driver = new FirefoxDriver();
                logger.info("Firefox Driver created");
                break;
            case EDGE:
                System.setProperty("webdriver.gecko.driver", "src/main/resources/msedgedriver.exe");
                driver = new EdgeDriver();
                logger.info("Edge Driver created");
            default:
                logger.error("Unsupported browser type: {}", browserTypeEnum);
                throw new IllegalArgumentException("Unsupported browser type: " + browserTypeEnum);
        }

        driver.manage().window().maximize();
        logger.info("WebDriver created for browser: {}", browserTypeEnum);
        return driver;
    }
}
