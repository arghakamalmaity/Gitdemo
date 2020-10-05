package stepDefinitions;

import static io.restassured.RestAssured.given;

import static org.junit.Assert.*;

import java.io.IOException;

import resource.APIResources;
import resource.TestDataBuild;
import resource.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefinition extends Utils{
	
	ResponseSpecification resp;
	RequestSpecification res;
	Response response;
	static String place_id;
	TestDataBuild td = new TestDataBuild();
	@Given("Add place payload {string} {string} {string}")
	public void add_place_payload(String Address, String Name, String Language) throws IOException {
		
		
		
		res = given().spec(requestSpecificationBuilder()).body(td.testdata(Address,Name,Language));
	    
	}

	@When("user call {string} with {string} http request")
	public void user_call_with_http_request(String resource, String httpRequestType) {
		
		 APIResources resourceAPI=APIResources.valueOf(resource);
		// System.out.println(resourceAPI.getResource());
		resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();	
		
		if(httpRequestType.equalsIgnoreCase("post"))
		response = res
				.when().post(resourceAPI.getResource());
		else if(httpRequestType.equalsIgnoreCase("get"))
			response = res
			.when().get(resourceAPI.getResource());
	    
	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
	   assertEquals(response.getStatusCode(),200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String expectedvalue) {
		
		assertEquals(getJsonPathValues(response, keyvalue),expectedvalue);
		
		
	  
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {
		
		place_id = getJsonPathValues(response, "place_id");
		res=given().spec(requestSpecificationBuilder()).queryParam("place_id", place_id);
		user_call_with_http_request(resource, "GET");
		String actualName = getJsonPathValues(response, "name");
		assertEquals(actualName,expectedName);
	    
	}
	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
		
		res=given().spec(requestSpecificationBuilder()).body(td.deletePayload(place_id));
	    
	}

}
