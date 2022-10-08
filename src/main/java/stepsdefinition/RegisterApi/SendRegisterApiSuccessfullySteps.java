package stepsdefinition.RegisterApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.JsonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SendRegisterApiSuccessfullySteps {
	String url, method, requestBodyFileName;
	

	@Given("I have method and url register api")
	public void i_have_method_and_url_register_api(List<Map<String, String>> requestData) {
		for (Map<String, String> data : requestData) {
			url = data.get("Url");
			method = data.get("Method");
			requestBodyFileName = data.get("RequestBodyFileName");
		}   
	}
	

	@When("I send request body register with {string} and {string} and {string} and {string}")
	public void i_send_request_body_register(String fieldName1, String value1, String fieldName2,String value2 ) {
	   HashMap<String, String> fieldNameValues = new HashMap<String, String>();
	   fieldNameValues.put(fieldName1, value1);
	   fieldNameValues.put(fieldName2, value2);
	   JsonUtils jsonUtils = new JsonUtils();
	   
	   
 	}

	@Then("I verify the {int} and {string}")
	public void i_verify_the_and_token(int expectedStatus, String expectedToken) {
	

	
}
}
