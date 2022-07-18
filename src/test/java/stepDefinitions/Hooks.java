package stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.path.json.JsonPath;


public class Hooks {

	private static final Logger log = LogManager.getLogger(Hooks.class);
	
@Before("@updateUser")
public void createUser() {
	
	StepDefinitions s = new StepDefinitions();
	s.payload();
	s.i_call_api_with_post_method("CreateMultipleUsers", "POST");
	s.api_call_is_successful_with_status(200);	
}


@Before("@updatePet")
public void createPetWithPendingStatus() {	
	StepDefinitions s = new StepDefinitions();
	s.i_create_add_pet_payload_with_canine_tom_available_test_url_com_dog_adult
		("Canine", "Ryan", "pending", "testUrl.com", "dog,baby");
	s.i_call_api_with_post_method("AddPet", "POST");
	JsonPath js = new JsonPath(s.response.body().asString());
	s.pet.setId(js.getLong("id"));	
}

@Before
public void beforeStep() {
	log.info("Starting Scenario Execution......");
	
}

@After
public void afterStep() {
	log.info("Ending Scenario Execution......");
	
}

@After("@updatePet")
public void deletePetWithPendingStatus() {	
//TODO: Delete the pet after the scenario is complete	
}
	
	
}
