@SingerLoginApi
Feature: I want to check invalid url and method
#TC1,2
Scenario Outline: I want to check invalid url and method
    Given I have "<Url>" and "<Method>"
    When I send request Invalid UserLoginApi
    Then The response return Invalid UserLoginApi "<StatusCode>" 
    Examples:
    | Url                           | Method | StatusCode|
    | https://reqrest.in/api/users/2|  GET   |     404   |
    | https://reqres.in/api/users/2 |  POST  |     405   |