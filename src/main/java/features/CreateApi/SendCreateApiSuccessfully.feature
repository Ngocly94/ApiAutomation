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
@CreateApi
Feature: I want to create user

  Scenario Outline: I want to create user
    Given I have "<Url>" and "<Method>" and "<Name>" and "<Job>"
     #| URL                         | Method | RequestBodyFileName|
     #| https://reqres.in/api/login | POST   | LoginRequest.json |
    When I sent request create user
    Then I verify the <StatusCode>
    Examples:
    | Name          |  Job   | Url                          | Method | StatusCode|
    |Lê Yến Ngọc    |Tester  | https://reqres.in/api/login  |  POST  |     201   |
    |Lê Yến Ngọc    |        | https://reqres.in/api/login  |  POST  |     201   |