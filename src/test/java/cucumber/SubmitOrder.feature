@tag
  Feature: Purchase the order from Ecommerce Website

    Background:
      Given I landed on Ecommerce Page

    @Regression
    Scenario Outline: Positive Test of Submitting the order

      Given Logged in with username <name> and password <password>
      When I add product <productName> from cart
      And Checkout <productName> and submit the order
      Then "ThankYou For The Order." message is displayed on ConfirmationPage

      Examples:
        | name                          | password      |  productName |
        | saitejaswiselenium@gmail.com  | Selenium@24   |  ZARA COAT 3 |