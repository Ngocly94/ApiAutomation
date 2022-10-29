
@RegisterApi
Feature: Check invalid url and method

  Scenario Outline: Check invalid url register api
    Given I have invalid "<Url>" register api and "<Method>"
    When I send request with invalid url register api
    Then I verify the invalid url register api "<Status>"

    Examples: 
      | Url                             | Method | Status|
      | https://reqrest.in/api/register | POST   | 400   |
      | https://reqres.in/api/register  | GET    | 405   |
 
