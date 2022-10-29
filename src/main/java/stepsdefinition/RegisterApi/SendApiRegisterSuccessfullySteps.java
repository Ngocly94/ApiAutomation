package stepsdefinition.RegisterApi;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import common.ApiUtils;
import common.JsonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SendApiRegisterSuccessfullySteps {
	String url, method, requestBodyFileName;
	int actualStatusCode;
	String actualToken;

	@Given("I have Url and Method and Request body register api")
	public void i_Have_Url_And_Method_Request_Body(List<Map<String, String>> requestData) {
		for (Map<String, String> data : requestData) {
			url = data.get("Url");
			method = data.get("Method");
			requestBodyFileName = data.get("RequestBodyFileName");
		}
	}

	@When("I send register api successfully")
	public void i_Send_Register_Api() {
		HttpResponse<String> response = null;
		JsonUtils jsonUtils = new JsonUtils();
		ApiUtils apiUtils = new ApiUtils();
		String projectPath = System.getProperty("user.dir");
		File sourceFile = new File(projectPath + "/src/main/resources/RegisterApi/" + requestBodyFileName);
		File destinationFile = new File(projectPath + "/src/main/resources/RegisterApi/" + "copy" + requestBodyFileName);
		System.out.print(destinationFile);
		jsonUtils.copyJsonFile(sourceFile, destinationFile);
		String requestBodyString;
		try {
			requestBodyString = Files.readString(destinationFile.toPath());
			response = apiUtils.sendPostRequest(url, requestBodyString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (response != null) {
	        actualStatusCode = response.statusCode();
	        actualToken = jsonUtils.getValueByKey(response.body(),"token");    
			}
		
		
		
	}

	@Then("I verify the StatusCode {int} and Token {string}")
	public void i_Verify_The_Status_Code_And_Token(int expectedStatusCode, String token) {
		assertEquals(actualStatusCode, expectedStatusCode);
		assertEquals(actualToken, token);
	}

}
