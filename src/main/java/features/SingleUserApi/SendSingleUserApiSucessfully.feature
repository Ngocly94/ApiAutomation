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
@SingerLoginApi
Feature: I want to get singer user
  @Maincase
  Scenario: I want to get singer user
    Given I have url and method
      | Url                           | Method | 
      | https://reqres.in/api/users/2 |  GET   |
    When I send request UserLoginApi
    Then The response return status code 200 and id 2
      
      