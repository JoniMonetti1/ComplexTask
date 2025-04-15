package tests;

import constants.AppConstants;
import driver.BrowserTypeEnum;
import driver.DriverFactory;
import driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.InventoryPage;
import pages.LoginPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

public class LoginTest {
    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    @AfterEach
    public void tearDown() {
        logger.info("Tearing down test");
        DriverManager.getInstance().quitDriver();
    }

    @ParameterizedTest(name = "Login with empty credentials: {0} and {1}")
    @MethodSource("data.TestDataProvider#provideCredentialsForEmptyTest")
    public void testLoginWithEmptyCredentials(String username, String password) {
        logger.info("Running test: UC-1 - Login with empty credentials");

        DriverManager.getInstance().setDriver(DriverFactory.createDriver(BrowserTypeEnum.FIREFOX));

        LoginPage loginPage = new LoginPage();
        loginPage.navigateToLoginPage();

        loginPage.enterUserName(username)
                .enterPassword(password)
                .clearUserName()
                .clearPassword()
                .clickLoginButton();

        String errorMessage = loginPage.getErrorMessage();
        assertThat("Error message for empty credentials", errorMessage, equalTo(AppConstants.USERNAME_REQUIRED));

        logger.info("Test empty credentials passed");
    }

    @ParameterizedTest(name = "Login with empty credentials: {0} and {1}")
    @MethodSource("data.TestDataProvider#provideCredentialsForEmptyTest")
    public void testLoginWithMissingPassword(String username, String password) {
        logger.info("Running test: UC-2 - Login with empty password");

        DriverManager.getInstance().setDriver(DriverFactory.createDriver(BrowserTypeEnum.FIREFOX));

        LoginPage loginPage = new LoginPage();
        loginPage.navigateToLoginPage();

        loginPage.enterUserName(username)
                .enterPassword(password)
                .clearPassword()
                .clickLoginButton();

        String errorMessage = loginPage.getErrorMessage();
        assertThat("Error message for empty password", errorMessage, equalTo(AppConstants.PASSWORD_REQUIRED));

        logger.info("Test missing password passed");
    }

    @ParameterizedTest(name = "Login with empty credentials: {0} and {1}")
    @MethodSource("data.TestDataProvider#provideValidCredentials")
    public void testLoginWithValidCredentials(String username, String password) {
        logger.info("Running test: UC-3 - Login with valid credentials should redirect to inventory page");

        DriverManager.getInstance().setDriver(DriverFactory.createDriver(BrowserTypeEnum.FIREFOX));

        LoginPage loginPage = new LoginPage();
        loginPage.navigateToLoginPage();

        Object resultPage = loginPage.login(username, password);

        assertThat("Page should be instance of InventoryPage", resultPage, instanceOf(InventoryPage.class));

        InventoryPage inventoryPage = (InventoryPage) resultPage;
        String title = inventoryPage.getTitle();
        assertThat("Inventory page title", title, equalTo(AppConstants.INVENTORY_PAGE_TITLE));
    }
}
