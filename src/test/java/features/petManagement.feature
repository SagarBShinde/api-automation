#Author: sagarshinde3258@gmail.com

@petManagement
Feature: Validate pet store pet management APIs


  @addPet
  Scenario Outline: Verify pet is added successfully
    Given I create add pet payload with "<category>","<name>","<status>","<photourl>","<tags>"
    When I call "AddPet" API with "POST" method
    Then API call is successful with Status 200

    Examples: 
      | category  | name 		| status  	| photourl 			| tags				|
      | canine 		| Tom     | available | testUrl1.com	| dog,adult		|
      | feline 		| Casey 	| sold    	| testUrl2.com 	| cat,baby 	  |
      | canine 		| Daisey  | pending		| testUrl3.com	| dog,senior	|
      | feline 		| Sophie 	| sold    	| testUrl4.com 	| cat,Adult 	|

  @updatePet
  Scenario: Verify Pet Details are updated correctly
    Given I get the details of the pet with "sold" status
    And I update the pet status as "pending"
    And I update the pet name as "Mark"
    And I create the UpdatePet payload
    When I call the "UpdatePet" API
 		Then API call is successful with Status 200
 		When I get the details of the pet by petId
 		Then "status" in the response body is "pending"
    And "name" in the response body is "Mark"
		
    