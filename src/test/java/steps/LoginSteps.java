package steps;

import driver.BrowserTypeEnum;
import driver.DriverFactory;
import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.InventoryPage;
import pages.LoginPage;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginSteps {
    private static final Logger logger = LoggerFactory.getLogger(LoginSteps.class);
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    /*
     * This method is called before each scenario to set up the WebDriver instance.
     */
    @Before
    public void setUp() {
        logger.info("Setting up test");
        BrowserTypeEnum browserType = DriverFactory.getBrowserTypeFromSystemProperty();
        logger.info("Using browser: {}", browserType);
        DriverManager.getInstance().setDriver(DriverFactory.createDriver(browserType));
        loginPage = new LoginPage();
    }

    /*
     * This method is called after each scenario to quit the WebDriver instance.
     */
    @After
    public void tearDown() {
        logger.info("Tearing down test");
        DriverManager.getInstance().quitDriver();
    }

    @Given("I am on the SauceDemo login page")
    public void i_am_on_the_saucedemon_login_page() {
        logger.info("Navigating to SauceDemon login page");
        loginPage.navigateToLoginPage();
    }

    @When("I enter username {string}")
    public void i_enter_username(String username) {
        logger.info("Entering username: {}", username);
        loginPage.enterUserName(username);
    }

    @When("I enter password {string}")
    public void i_enter_password(String password) {
        logger.info("Entering password: {}", password);
        loginPage.enterPassword(password);
    }

    @When("I enter wrong username {string}")
    public void i_enter_wrong_username(String username) {
        logger.info("Entering wrong username: {}", username);
        loginPage.enterUserName(username);
    }

    @When("I enter wrong password {string}")
    public void i_enter_wrong_password(String password) {
        logger.info("Entering wrong password: {}", password);
        loginPage.enterPassword(password);
    }

    @When("I clear the username field")
    public void i_clear_username_field() {
        logger.info("Clearing username field");
        loginPage.clearUserName();
    }

    @When("I clear the password field")
    public void i_clear_password_field() {
        logger.info("Clearing password field");
        loginPage.clearPassword();
    }

    @When("I click the login button")
    public void i_click_login_button() {
        logger.info("Clicking login button");
        loginPage.clickLoginButton();
    }

    @Then("I should see the error message {string}")
    public void i_should_see_error_message(String expectedErrorMessage) {
        logger.info("Verifying error message: {}", expectedErrorMessage);
        String actualErrorMessage = loginPage.getErrorMessage();
        assertEquals("Error message should match", actualErrorMessage, expectedErrorMessage);
    }

    @Then("I should be redirected to the inventory page")
    public void i_should_be_redirected_to_inventory_page() {
        logger.info("Verifying redirection to inventory page");
        assertTrue(loginPage.isLoginSuccessful(), "login should be successful");
        inventoryPage = loginPage.getInventoryPage();
    }

    @Then("I should see the title {string}")
    public void i_should_see_the_title(String expectedTitle) {
        logger.info("Verifying title: {}", expectedTitle);
        String actualTitle = inventoryPage.getTitle();
        assertEquals("Title should match", actualTitle, expectedTitle);
    }
}
