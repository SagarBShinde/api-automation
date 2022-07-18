package stepDefinitions;

import java.math.BigDecimal;
import java.util.ArrayList;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import automation.pojo.pet.Category;
import automation.pojo.pet.Pet;
import automation.pojo.pet.Tag;
import automation.pojo.user.PetStoreUser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.ApiEndpoints;
import utils.Utils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;


public class StepDefinitions extends StepDefHelper{
	
	
	private static final Logger log = LogManager.getLogger(StepDefinitions.class);
	protected PetStoreUser petStoreUser;
	protected Pet pet;


	@Given("Create multiple users payload")
	public  void payload() {
		 this.requestSpec = given()
				 			.spec(this.requestSpec)
				 			.body(this.getDataProvider().CreateUserWithArrayPayload());
	}
	

	@Given("I get details of the User with user name {string}")
	public void i_get_details_of_the_user_with_user_name(String userName) {
		this.requestSpec = given().spec(this.requestSpec);
		log.info("ApiEndPoint is:"+ ApiEndpoints.valueOf("GetUserByUserName").getEndPoint()+"/"+userName);
		this.response = executeHttpMethod("GET", ApiEndpoints.valueOf("GetUserByUserName").getEndPoint()+"/"+userName, this.getRequestSpecification());
		log.info("Response is" + this.response.asPrettyString());
		assertEquals(response.getStatusCode(), 200);
		Gson gson = new Gson();
		petStoreUser = gson.fromJson(this.response.body().asString(), PetStoreUser.class);
	}
	

	@When("I get the details of the pet by petId")
	public void i_get_the_details_of_the_pet_by_pet_id() {
		this.response = 
				 executeHttpMethod("GET", ApiEndpoints.valueOf("GetPet").getEndPoint() + "/"+ pet.getId(),this.getRequestSpecification())
				.then()
				.spec(this.getResponseSpecification())
				.extract()
				.response();
				
		log.debug(response.asPrettyString());
		
	}



	@Given("I update the email as {string}")
	public void i_update_the_email_as(String updatedEmail) {
		log.debug("Updated value:"+ updatedEmail);
		petStoreUser.setEmail(updatedEmail);		
	}
	
	@Given("I update the userName as {string}")
	public void i_update_the_userName_as(String updatedUserName) {
		log.debug("Updated value:"+ updatedUserName);
		petStoreUser.setUserName(updatedUserName);		
	}
	
	@Given("I create UpdateUser payload")
	public void i_create_update_user_payload() {
		 this.requestSpec = given()
		 			.spec(this.requestSpec)
		 			.body(this.petStoreUser);
	}

	@When("I call {string} API with userId {string}")
	public void i_call_api(String apiEndPoint, String userName) {
		this.response = 
				 executeHttpMethod("PUT", ApiEndpoints.valueOf(apiEndPoint).getEndPoint() + "/"+ userName,this.getRequestSpecification())
				.then()
				.spec(this.getResponseSpecification())
				.extract()
				.response();
				
		log.debug(response.asPrettyString());
	}
	
	@When("I call the {string} API")
	public void i_call_the_api(String apiEndPoint) {
		this.response = 
				 executeHttpMethod("PUT", ApiEndpoints.valueOf(apiEndPoint).getEndPoint(),this.getRequestSpecification())
				.then()
				.spec(this.getResponseSpecification())
				.extract()
				.response();
				
		log.debug(response.asPrettyString());
	}
	
	@Given("I get the details of the pet with {string} status")
	public void i_get_the_details_of_the_pet_with_pending_status(String statusValue) {
		
		RequestSpecification rspec = given().spec(this.requestSpec).queryParam("status", statusValue);
		log.info("ApiEndPoint is:"+ ApiEndpoints.valueOf("FindPetByStatus").getEndPoint());
		this.response = executeHttpMethod("GET", ApiEndpoints.valueOf("FindPetByStatus").getEndPoint(), rspec);
		log.info("Response is" + this.response.asPrettyString());
		assertEquals(response.getStatusCode(), 200);
		Gson gson = new Gson();
		Pet[] petArray = gson.fromJson(this.response.body().asString(), Pet[].class);
		assertTrue(petArray.length > 0 );
		this.pet = petArray[0];
	  
		
	}
	@Given("I update the pet status as {string}")
	public void i_update_the_pet_status_as(String statusValue) {
		this.pet.setStatus(statusValue);
	}
	@Given("I update the pet name as {string}")
	public void i_update_the_pet_name_as(String updatedName) {
		this.pet.setName(updatedName);
	}
	
	@Given("I create the UpdatePet payload")
	public void i_create_the_update_pet_payload() {
		this.pet.setId(this.pet.getId()); 
		this.requestSpec = given()
		 			.spec(this.requestSpec)
		 			.body(this.pet);
	}
	
	@When("I call {string} API with {string} method")
	public void i_call_api_with_post_method(String path, String httpMethod) {
		log.debug("Value of path:"+ path);
		log.debug("Value of httpMethod:"+ httpMethod);
		log.debug("Value of httpMethod:"+ ApiEndpoints.valueOf(path).getEndPoint());
		
		this.response = 
				 executeHttpMethod(httpMethod, ApiEndpoints.valueOf(path).getEndPoint(),this.getRequestSpecification())
				.then()
				.spec(this.getResponseSpecification())
				.extract()
				.response();
				
		log.debug(response.asPrettyString());
		
	}
	@Then("API call is successful with Status {int}")
	public void api_call_is_successful_with_status(int responseCode) {
		log.debug("Response code received from the API:" +response.getStatusCode());
		assertEquals(response.getStatusCode(),responseCode);
	 
	}
	
	@Then("{string} in the response body is {string}")
	public void status_in_the_response_body_is_ok(String path, String Expectedvalue) {
		String responseAsString = response.asString();
		log.debug("Response String is:"+ responseAsString);
		JsonPath js = new JsonPath(responseAsString);
		assertEquals(js.get(path), Expectedvalue);
		
	}
	
	@Given("I create add pet payload with {string},{string},{string},{string},{string}")
	public void i_create_add_pet_payload_with_canine_tom_available_test_url_com_dog_adult
		(String category, String name, String status, String photoUrls, String tags) {
		
		this.pet = new Pet();
		Category cat = new Category();
		cat.setId(0);
		cat.setName(category);
		
		pet.setCategory(cat);
		pet.setId(0);
		pet.setName(name);
		pet.setStatus(status);
		pet.setPhotoUrls(Utils.convertStringToArray(photoUrls));
		
		String[] tagValues = Utils.convertStringToArray(tags);
		List<Tag> tagList = new ArrayList<Tag>();
		for (String tagValue : tagValues) {
			Tag t = new Tag();
			t.setId(0);
			t.setName(tagValue);
			tagList.add(t);
		}
		
		pet.setTags(tagList.toArray(new Tag[tagList.size()]));
		
		log.debug("Pet Payload is:" + new Gson().toJson(pet, Pet.class));
		
		 this.requestSpec = given()
		 			.spec(this.requestSpec)
		 			.body(this.pet);
		
		
	}



	private Response executeHttpMethod(String httpMethod, String path, RequestSpecification requestSpec) {
		Response res = this.response;
		this.requestSpec.log().body();
		switch (httpMethod) {
		 case "GET" :
			 res = requestSpec.when().get(path);
			 break;
		 case "POST" :
			 res = requestSpec.when().post(path);
			 break;
		 case "PUT" :
			 res = requestSpec.when().put(path);	 
			 break;
		}
		
		return res;	
	}	
}
