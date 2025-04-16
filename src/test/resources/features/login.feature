Feature: SauceDemo Login
  As a user
  I want to be able to login to SauceDemo
  So that I can access the inventory

  Background:
    Given I am on the SauceDemo login page

    Scenario Outline: UC-1 Login with empty credentials
      When I enter wrong username "<username>"
      And I enter wrong password "<password>"
      And I clear the username field
      And I clear the password field
      And I click the login button
      Then I should see the error message "Epic sadface: Username is required"

      Examples:
        | username         | password         |
        | wrong_username   | wrong_password   |


    Scenario Outline: UC-2 Login with empty password
      When I enter username "<username>"
      And I enter wrong password "<password>"
      And I clear the password field
      And I click the login button
      Then I should see the error message "Epic sadface: Password is required"

        Examples:
            | username         | password         |
            | standard_user    | wrong_password   |

    Scenario Outline: UC-3 - Login with valid credentials should redirect to inventory page
      When I enter username "<username>"
      And I enter password "<password>"
      And I click the login button
      Then I should be redirected to the inventory page
      And I should see the title "Swag Labs"

      Examples:
        | username                | password         |
        | standard_user           | secret_sauce     |
        | performance_glitch_user | secret_sauce     |