package stepsdefinition.DeleteApi;

import static org.testng.Assert.assertEquals;

import java.awt.image.RescaleOp;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import org.opendope.questions.Response;

import common.ApiUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DeleteApiSuccessfullyStep {
	String url, method;
	int actualStatus;
  @Given("I have Url, Method Delete Api")
  public void given_I_Have_Url_Method_Delete_Api(List<Map<String, String>> requestData) {
		for (Map<String, String> data : requestData) {
			url = data.get("Url");
			method = data.get("Method");
		}
  }

  @When("I send request Delete Api")
  public void when_I_Send_Request_Delete_Api() {
	  ApiUtils apiUtils = new ApiUtils();
	  HttpResponse<String> response = null;
	  response = apiUtils.sendDeleteRequest(url);
	  actualStatus = response.statusCode();
	  
  }

  @Then("I verify Status {int}")
  public void then_I_Verify_Status_Code(int expectedStatus) {
	  assertEquals(actualStatus, expectedStatus);
  }

}
