@LoginApi
Feature: Check response when send invalid method or URL

  @MainCase
  Scenario: Check response when send invalid method or URL
    Given I have "<Url>" and "<Method>" and "<RequestBodyFileName>"
    When  I send the request
    Then The response returns "<StatusCode>" and "<ErrorMessage>"

    Examples: 
      | Url                           | Method | RequestBodyFileName|StatusCode |ErrorMessage  |
      | https://reqrest.in/api/login  | POST   | LoginRequest.json  |400        | Not found    |
      | htps://reqres.in/api/login    | GET    | LoginRequest.json  |400        |Invalid Method| 