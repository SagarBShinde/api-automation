#Author: sagarshinde3258@gmail.com

@tag
Feature: Validate petstore user management APIs

  @userManagement @addUserWithArray
  Scenario: Verify multiple users can be added using createWithArray Endpoint
    Given Create multiple users payload
    When I call "CreateMultipleUsers" API with "POST" method
    Then API call is successful with Status 200
    And "message" in the response body is "ok"
    
  @userManagement @updateUser 
  Scenario: Verify update user call with PUT method is successful
    Given I get details of the User with user name "HSpecter"
    Given I update the userName as "HarveyS"
    Given I update the email as "Harvey.Specter@ps.com"
    Given I create UpdateUser payload
    When I call "UpdateUser" API with userId "HSpecter"
    Then API call is successful with Status 200
    
   @userManagement @verifyUserUpdate
   Scenario: Verify user details are updated correctly
     Given I get details of the User with user name "HarveyS"
     Then API call is successful with Status 200
     And "username" in the response body is "HarveyS"
     And "email" in the response body is "Harvey.Specter@ps.com"
    
    
    
    

