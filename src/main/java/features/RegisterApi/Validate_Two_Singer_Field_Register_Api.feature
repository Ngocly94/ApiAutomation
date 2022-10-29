@RegisterApi
Feature: I want to validate two fields register api

  Scenario Outline: I want to validate two fields register api
    Given I have url and method and request body register api 
      | Url                         | Method | RequestBodyFileName|
      | https://reqres.in/api/register | POST   | RegisterRequest.json |
      
    When I send request body register with "<FieldName1>" and "<Value1>" and "<FieldName2>" and "<Value2>"
    Then I verify the "<Expected Status Code>" and "<Expected Token>" register api

    Examples: 
      | FieldName1 | Value1               | FieldName2 | Value2               | Expected Status Code | Expected Token  |
      | email      | n@.com               | password   |               123456 |                  200 | QpwL5tke4Pnpja7X4 |
      | email      | ngocle1111@gmail.com | password   | ngocle111111mail.com |                  200 | QpwL5tke4Pnpja7X4 |
