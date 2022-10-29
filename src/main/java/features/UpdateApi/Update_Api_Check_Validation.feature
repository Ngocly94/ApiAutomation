#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@UpdateApi
Feature: I want to check validation Single field Update Api

  Scenario Outline: I want to check validation Single field Update Api
    Given I have Url, Method, Requestbody Update Api_Validation Single field
      | Url                           | Method | RequestBodyFileName |
      | https://reqres.in/api/users/2 | PUT    | UpdateRequest.json  |
    When I send request body Update Api with "<FieldName>" and "<Value>"
    Then I verify "<Expected Status Code>" Update Api
    Examples:
      | FieldName | Value   | Expected Status Code |
      | name      | missing |                  400 |
      | name      | null    |                  400 |
      | name      | ""      |                  400 |
      | name      | !$%     |                  400 | 
      | job       | missing |                  400 |
      | job       | null    |                  400 |
      | job       | ""      |                  400 | 
      | job       | !$%     |                  400 | 
