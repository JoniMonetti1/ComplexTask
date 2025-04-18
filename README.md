Task description
Launch URL: https://www.saucedemo.com/
Test Cases
UC-1: Test Login form with empty credentials

Type any credentials into "Username" and "Password" fields
Clear the inputs
Hit the "Login" button
Check the error messages: "Username is required"

UC-2: Test Login form with credentials by passing Username

Type any credentials in username
Enter password
Clear the "Password" input
Hit the "Login" button
Check the error messages: "Password is required"

UC-3: Test Login form with credentials by passing Username & Password

Type credentials in username which are under Accepted username are sections
Enter password as "secret_sauce"
Click on Login
Validate the title "Swag Labs" in the dashboard

Requirements

Provide parallel execution
Add logging for tests
Use Data Provider to parametrize tests
Make sure that all tasks are supported by these 3 conditions: UC-1; UC-2; UC-3

Technical Stack
Test Automation tool: Selenium WebDriver
Project Builder: Maven
Browsers:

Firefox
Edge
Locators: XPath
Test Runner: JUnit

Optional Components
Patterns:

Singleton
Adapter
Strategy

Test automation approach: BDD
Assertions: Hamcrest
Loggers: SLF4J
