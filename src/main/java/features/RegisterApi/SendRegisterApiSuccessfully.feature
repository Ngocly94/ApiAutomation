
@RegisterApi
Feature: I want to register api account
  @Maincase
  Scenario: I want to register api account
    Given I have Url and Method and Request body register api
     | Url                            | Method | RequestBodyFileName  |
     | https://reqres.in/api/register | POST   | RegisterRequest.json |
    When I send register api successfully
    Then I verify the StatusCode 200 and Token "QpwL5tke4Pnpja7X4"

