package stepDefinitions;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import testDataManagement.ProvidesTestData;
import testDataManagement.ProvidesTestDataImpl;
import utils.Utils;

public class StepDefHelper {
	
	
	protected RequestSpecification requestSpec;
	protected ResponseSpecification responseSpec;
	protected static final String DEFAULT_BASE_URI = "https://petstore.swagger.io/v2";
	protected Response response;
	// Different implementation of Data provider can be plugged in from here
	protected ProvidesTestData dataProvider;
	
	public StepDefHelper() {
		
	
		this.dataProvider = new ProvidesTestDataImpl();
		this.requestSpec = setUpRequest();
		this.responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
	}
	
	public RequestSpecification getRequestSpecification() {
		
		return requestSpec;
	}
	
	public ResponseSpecification getResponseSpecification() {

		return responseSpec;
	}
	
	public ProvidesTestData getDataProvider() {
	
		return dataProvider;
		
	}
	
	private RequestSpecification setUpRequest() {
		PrintStream log;
		RequestSpecification res = this.requestSpec;
		if (this.requestSpec == null) {
		try {
			log = new PrintStream(new FileOutputStream("api-logs.log"));
			res= new RequestSpecBuilder()
			.setBaseUri(Utils.getProperty("baseUrl"))
			.setContentType(ContentType.JSON)
			.addFilter(RequestLoggingFilter.logRequestTo(log))
			.addFilter(ResponseLoggingFilter.logResponseTo(log))
			.build();		
		} catch (IOException e) {
			e.printStackTrace();
			res = new RequestSpecBuilder()
					.setBaseUri(DEFAULT_BASE_URI)
					.setContentType(ContentType.JSON)
					.build();
			
			}
		}
		return res;
	}
  }
