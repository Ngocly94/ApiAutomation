@LoginApi
Feature: Check response when send request successfully

  @MainCase
  Scenario: Check response when send request successfully
    Given I have url and method and request body
      | URL                         | Method | RequestBodyFileName|
      | https://reqres.in/api/login | POST   | LoginRequest.json |
    When I send request 
    Then Response return status code 200 and token "QpwL5tke4Pnpja7X4"