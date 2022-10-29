package stepsdefinition.RegisterApi;

import static org.testng.Assert.assertEquals;

import java.net.http.HttpResponse;

import common.ApiUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Data;

public class Check_Invalid_Method_UrlSteps {
	String url, method;
	HttpResponse<String> response;
	int actualStatusCode;

	@Given("I have invalid {string} register api and {string}")
	public void i_have_invalid_register_api_and(String inputUrl, String inputMethod) {
		url = inputUrl;
		method = inputMethod;
		System.out.println("ur: " + url);
	}

	@When("I send request with invalid url register api")
	public void i_send_request_with_invalid_url_register_api() {
		ApiUtils apiUtils = new ApiUtils();
		HttpResponse<String> response = null;
		  if(method.equals("GET")) {
			  response=apiUtils.sendGetRequest(url);
		  } else if ((method.equals("POST"))){
			  response = apiUtils.sendPostRequestNoBody(url);
		  }
		  if(response!=null) {
		  actualStatusCode = response.statusCode();
		  String body= response.body();
		  }
		}

	@Then("I verify the invalid url register api {string}")
	public void i_verify_the_invalid_url(String expectStatusCode) {
		assertEquals(actualStatusCode, Integer.parseInt(expectStatusCode));

	}
	

}
