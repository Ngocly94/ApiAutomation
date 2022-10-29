@DeleteApi
Feature: I want to delete Api

  @Maincase
  Scenario: I want to Delete Api
    Given I have Url, Method Delete Api
      | Url                             | Method | 
      | https://reqres.in/api/users/2   | DELETE |
    When I send request Delete Api
    Then I verify Status 204
