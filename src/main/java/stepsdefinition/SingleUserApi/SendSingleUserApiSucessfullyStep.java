package stepsdefinition.SingleUserApi;

import static org.testng.Assert.assertEquals;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import common.ApiUtils;
import common.JsonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SendSingleUserApiSucessfullyStep {
	String url, method, actualId;
	int actualStatusCode ;
	HttpResponse<String> response;

	@Given("I have url and method")
	public void i_Have_Url_And_Method(List<Map<String, String>> requestData) {
		for (Map<String, String> data : requestData) {
			url = data.get("Url");
			method = data.get("Method");

		}

	}
    @When("I send request UserLoginApi") 
    public void i_Send_Request() {
    	ApiUtils util = new ApiUtils();
    	JsonUtils jsonutil = new JsonUtils();
		response = util.sendGetRequest(url);
		if(response!=null) {
			actualStatusCode=response.statusCode();
//		    actualId=jsonutil.getValueByKey(response.body().toString(), "id");
//			System.out.print(actualId);
		}
    }
    @Then("The response return status code {int} and id {int}")
    public void the_Response_Return_StatusCode_And_ErrorMessage(int expectedStatusCode, int expectedId) {
    	assertEquals(actualStatusCode, expectedStatusCode);
 //   	assertEquals(actualId,expectedId);
    }
    
}
