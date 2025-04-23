package tests;

import constants.AppConstants;
import driver.BrowserTypeEnum;
import driver.DriverFactory;
import driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.InventoryPage;
import pages.LoginPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        logger.info("Setting up test environment");
        BrowserTypeEnum browserType = DriverFactory.getBrowserTypeFromSystemProperty();
        logger.info("Using browser: {}", browserType);
        DriverManager.getInstance().setDriver(DriverFactory.createDriver(browserType));

        loginPage = new LoginPage();
        loginPage.navigateToLoginPage();
    }

    @AfterEach
    public void tearDown() {
        logger.info("Tearing down test");
        DriverManager.getInstance().quitDriver();
    }

    @ParameterizedTest(name = "Login with empty credentials: {0} and {1}")
    @MethodSource("data.TestDataProvider#provideCredentialsForEmptyTest")
    public void testLoginWithEmptyCredentials(String username, String password) {
        logger.info("Running test: UC-1 - Login with empty credentials");

        loginPage.enterUserName(username)
                .enterPassword(password)
                .clearUserName()
                .clearPassword()
                .clickLoginButton();

        String errorMessage = loginPage.getErrorMessage();
        assertThat("Error message for empty credentials", errorMessage, equalTo(AppConstants.USERNAME_REQUIRED));

        logger.info("Test empty credentials passed");
    }

    @ParameterizedTest(name = "Login with empty password: {0}")
    @MethodSource("data.TestDataProvider#provideCredentialsForEmptyTest")
    public void testLoginWithMissingPassword(String username) {
        logger.info("Running test: UC-2 - Login with empty password");

        loginPage.enterUserName(username)
                .enterPassword(AppConstants.WRONG_PASSWORD)
                .clearPassword()
                .clickLoginButton();

        String errorMessage = loginPage.getErrorMessage();
        assertThat("Error message for empty password", errorMessage, equalTo(AppConstants.PASSWORD_REQUIRED));

        logger.info("Test missing password passed");
    }

    @ParameterizedTest(name = "Login with valid credentials: {0} and {1}")
    @MethodSource("data.TestDataProvider#provideValidCredentials")
    public void testLoginWithValidCredentials(String username, String password) {
        logger.info("Running test: UC-3 - Login with valid credentials should redirect to inventory page");

        loginPage.attemptLogin(username, password);

        assertTrue(loginPage.isLoginSuccessful(), "Login should be successful");

        InventoryPage inventoryPage = loginPage.getInventoryPage();

        assertThat("Page should be instance of InventoryPage", inventoryPage, instanceOf(InventoryPage.class));

        boolean inventoryTitleIsValid = inventoryPage.verifyTitle(AppConstants.INVENTORY_PAGE_TITLE);
        assertTrue(inventoryTitleIsValid, "Inventory page title is not as expected");
    }
}
