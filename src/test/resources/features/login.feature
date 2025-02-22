Feature: Use login
  As a valid user I should be able to
  login to the system with valid credentials
  and view product inventory page


  Scenario: Login with valid credentials
    Given user has accessed the login page
    When user provides valid credentials
    Then the user should be directed to the product inventory page


  Scenario: Login with invalid password
    Given user has accessed the login page
    When user provides invalid password "standard_user","invalid"
    Then the user should be see error message "Epic sadface: Username and password do not match any user in this service"

#  Scenario Outline: Login with invalid credentials
#    Given user has accessed the login page
#    When user provides invalid credentials "<username>",
#    Examples: