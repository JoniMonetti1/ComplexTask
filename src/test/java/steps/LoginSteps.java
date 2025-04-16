package steps;

import driver.BrowserTypeEnum;
import driver.DriverFactory;
import driver.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import pages.InventoryPage;
import pages.LoginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;


public class LoginSteps {
    private static final Logger logger = LoggerFactory.getLogger(LoginSteps.class);
    private LoginPage loginPage;
    private Object currentPage;

    @Before
    public void setUp() {
        logger.info("Setting up test");
        DriverManager.getInstance().setDriver(DriverFactory.createDriver(BrowserTypeEnum.FIREFOX));
    }

    @After
    public void tearDown() {
        logger.info("Tearing down test");
        DriverManager.getInstance().quitDriver();
    }

    @Given("I am on the SauceDemo login page")
    public void i_am_on_the_saucedemon_login_page() {
        logger.info("Navigating to SauceDemon login page");
        loginPage = new LoginPage();
        currentPage = loginPage.navigateToLoginPage();
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
        currentPage = loginPage.clickLoginButton();
    }

    @Then("I should see the error message {string}")
    public void i_should_see_error_message(String expectedErrorMessage) {
        logger.info("Verifying error message: {}", expectedErrorMessage);
        assertThat("Current page should be LoginPage", currentPage, instanceOf(LoginPage.class));
        String actualErrorMessage = ((LoginPage) currentPage).getErrorMessage();
        assertThat("Error message should match", actualErrorMessage, equalTo(expectedErrorMessage));
    }

    @Then("I should be redirected to the inventory page")
    public void i_should_be_redirected_to_inventory_page() {
        logger.info("Verifying redirection to inventory page");
        assertThat("Current page should be InventoryPage", currentPage, instanceOf(InventoryPage.class));
    }

    @Then("I should see the title {string}")
    public void i_should_see_the_title(String expectedTitle) {
        logger.info("Verifying title: {}", expectedTitle);
        assertThat("Current page should be InventoryPage", currentPage, instanceOf(InventoryPage.class));
        String actualTitle = ((InventoryPage) currentPage).getTitle();
        assertThat("Title should match", actualTitle, equalTo(expectedTitle));
    }
}
