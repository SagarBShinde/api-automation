package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import automation.pojo.user.PetStoreUser;
import io.cucumber.java.en.Given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

public class UserManagementStepDefinitions extends StepDefHelper {
	
//	@Given("Create multiple users payload")
//	public  void payload() {
//		 this.requestSpec = given().spec(this.getRequestSpecification())
//		.body(
//		this.getDataProvider().CreateUserWithArrayPayload());
//	//	responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
//	 
//	}
	

}
