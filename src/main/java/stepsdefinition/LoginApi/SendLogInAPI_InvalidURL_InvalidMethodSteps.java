package stepsdefinition.LoginApi;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import common.ApiUtils;
import common.JsonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SendLogInAPI_InvalidURL_InvalidMethodSteps {
	
	String url, method, requestBodyFileName;
	HttpResponse<String> response = null;
	int actualStatusCode;
	String actualErrorMessage;

@Given("I have {string} and {string} and {string}")
    public void givenIhaveURLAndMethod(List<Map<String, String>> requestData) {
		for (Map<String, String> data : requestData) {
			url = data.get("Url");
			method = data.get("Method");
			requestBodyFileName = data.get("RequestBodyFileName");
		}
    }
    @When("I send the request")
    public void whenISendTheRequest() { 
    	ApiUtils apiUtils = new ApiUtils();
   	  if(method.equals("GET")){
   		  response = apiUtils.sendGetRequest(url);
   	  } else if ((method.equals("POST"))){
   		  response = apiUtils.sendPostRequest(url, requestBodyFileName);
   	  }
   	  actualStatusCode = response.statusCode(); 
   	  String body= response.body();
    }
    @Then("The response returns {string} and {string}")
    public void thenTheResponseReturnsStatusCodeAndErrorMessage(int expectedStatusCode, String expectedErrorMessage) { 
    	assertEquals(actualStatusCode, expectedStatusCode);
    	
    }
}