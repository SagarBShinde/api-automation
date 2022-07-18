# api-automation

* [Introduction](https://github.com/SagarBShinde/api-automation/blob/master/README.md#introduction)
* [System Requirements](https://github.com/SagarBShinde/api-automation/blob/master/README.md#system-requirements)
* [Instructions To Run Tests](https://github.com/SagarBShinde/api-automation/blob/master/README.md#instructions-to-run-tests)
* [Framework Tech Stack](https://github.com/SagarBShinde/api-automation/blob/master/README.md#Framework-Tech-stack)
* [Scope and Test Coverage](https://github.com/SagarBShinde/api-automation/blob/master/README.md#Scope-and-Test-Coverage)
* [Future Enhancements](https://github.com/SagarBShinde/api-automation/blob/master/README.md#future-enhancements)






## Introduction
This is a simple API Automation framework based on Rest Assured + Cucumber BDD developed as a proof of concept. The framework intends to validate the some of APIs in https://petstore.swagger.io/v2 

## System Requirements
The code has been tested with following configurarion
  * Windows 10 v1909 Build 18363.1556
  * Java v 1.8.0_221
  * Apache Maven 3.3.9

## Instructions to run tests
1. Clone the repo from this [location](https://github.com/SagarBShinde/api-automation) on GitHub
2. Open a terminal and navigate to the parent repository 
   ``cd <Path to api-automation project>``
3. Running tests using Maven- Run the following command to start test execution
   ``mvn test verify``
         
4. Once the test execution is complete, Test results can be checked at the following location
        <api-automation project directory>/target/cucumber-html-reports/overview-features.html
5. Logs of execution can be found at <api-automation project directory>/logs/automationLogs.log

## Framework Tech stack
  
  * Platform : Java 1.8
  * Rest Client Library: Rest Assured
  * BDD Library: cucumber-java
  * Object serializing-deserializing: Google Gson
  * Unit Testing Framewok: Junit 4.x
  * Logging: Log4j2
  * Reporting: maven-cucumber-reporting

## Scope and Test Coverage

### API End points covered
* Pet Management
 * /pet POST Add a new pet to the Pet store
 * /pet PUT Update existing pet
 * /pet/findByStatus GET Find pets by Status
 * /pet/{petId} GET find pet by Pet Id
		
* User Management
 * user/createWithArray POST Create multiple users using array
 * /user/{userName} PUT Update existing user
 * /user/{userName} GET Get user details by user name
		
### Features
* userManagement.feature - 3 Scenarios
* petManagement.feature -  2 Scenarios

Note: Some of the scenarios in the userManagement.feature are failing due to incorrect behavior of the API as follows
* Users are not getting added even after a successful POST call on the user/createWithArray end point. I am getting 200 response for the POST call
* The User attribute is not getting updated even after successful response to the PUT call

## Future Enhancement
* Implement mocking
* Add Unit Tests
* Add a new implementation for Data provider
* Using dependency injection framework
* Use of Docker ??


    


    

 
   