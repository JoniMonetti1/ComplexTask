package pages;

import constants.AppConstants;

import java.util.Objects;

public class LoginPage extends BasePage{
    private static final String USERNAME_FIELD_XPATH = "//input[@id='user-name']";
    private static final String PASSWORD_FIELD_XPATH = "//input[@id='password']";
    private static final String LOGIN_BUTTON_XPATH = "//input[@id='login-button']";
    private static final String ERROR_MESSAGE_XPATH = "//h3[@data-test='error']";

    /**
     * Navigate to login page
     * @return LoginPage instance
     */
    public LoginPage navigateToLoginPage() {
        logger.info("Navigating to Login Page");
        driver.get(AppConstants.BASE_URL);
        return this;
    }

    /**
     * Clear username input and then enter username
     * @param userName username to enter
     */
    public LoginPage enterUserName(String userName) {
        logger.info("Entering User Name: {}", userName);
        clearUserName();
        typeElementByXPath(USERNAME_FIELD_XPATH, userName);
        return this;
    }

    /**
     * Clear username input
     */
    public LoginPage clearUserName() {
        logger.info("Clearing User Name");
        clearElementByXPath(USERNAME_FIELD_XPATH);
        return this;
    }

    /**
     * Clear password input and then enter password
     * @param password Password to enter
     */
    public LoginPage enterPassword(String password) {
        logger.info("Entering Password: {}", password);
        clearPassword();
        typeElementByXPath(PASSWORD_FIELD_XPATH, password);
        return this;
    }

    /**
     * Clear password input
     */
    public LoginPage clearPassword() {
        logger.info("Clearing Password");
        clearElementByXPath(PASSWORD_FIELD_XPATH);
        return this;
    }

    /**
     * Click login button
     * @return InventoryPage instance if login is successful else return LoginPage
     */
    public Object clickLoginButton() {
        logger.info("Clicking Login Button");
        clickElementByXPath(LOGIN_BUTTON_XPATH);

        if (isElementPresent(ERROR_MESSAGE_XPATH)) {
            String errorMessage = findElementByXPath(ERROR_MESSAGE_XPATH).getText();
            logger.error("Login failed with error message: {}", errorMessage);
            return this;
        } else {
            logger.info("Login successful");
            return new InventoryPage();
        }
    }

    /**
     * Get error message
     * @return Error message text
     */
    public String getErrorMessage() {
        logger.info("Getting Error Message");
        return findElementByXPath(ERROR_MESSAGE_XPATH).getText();
    }

    /**
     * Login with username and password
     * @param username Username
     * @param password Password
     * @return InventoryPage instance if login is successful else return LoginPage
     */
    public Object login(String username, String password) {
        logger.info("Performing Login with Username: {} and Password: {}", username, password);
        typeElementByXPath(USERNAME_FIELD_XPATH, username);
        typeElementByXPath(PASSWORD_FIELD_XPATH, password);
        return clickLoginButton();
    }
}
