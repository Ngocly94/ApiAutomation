package stepsdefinition.SingleUserApi;

import static org.testng.Assert.assertEquals;

import java.net.http.HttpResponse;

import common.ApiUtils;
import common.JsonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SendSingleApi_InvalidUrl_InvalidMethod_Step {
	String url, method;
	int actualStatusCode;

	@Given("I have {string} and {string}")
	public void i_Have_Url_And_Method(String inputUrl, String inputMethod) {
		url = inputUrl;
		method = inputMethod;

	}
	
	@When("I send request Invalid UserLoginApi")
	public void i_Send_Request_User_Login_Api (){
		HttpResponse<String> response=null;
		ApiUtils apiUtils = new ApiUtils();
		JsonUtils jsonUtils = new JsonUtils();
		if(method.equals("GET")) {
			response=apiUtils.sendGetRequest(url);	
		}
		if(method.equals("POST")) {
			response=apiUtils.sendPostRequest(url, null);
		}
		if(response != null)
		{
		actualStatusCode = response.statusCode();
		}	
		else{
			System.out.print("reponse null");	
		}
		System.out.print(actualStatusCode);
//		boolean bodyString = jsonUtils.checkErrorMessage(null, null);
}
    @Then("The response return Invalid UserLoginApi {string}")
    public void the_Response_Return_Stautus_Code (String expectedStatusCode){
    	assertEquals(actualStatusCode, Integer.parseInt(expectedStatusCode));
    	
    }
}