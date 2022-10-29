package stepsdefinition.UpdateUApi;

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

public class UpdateApiSuccessfullyStep1 {
	String url, method, requestBodyFileName;
	HttpResponse<String> response;
	String actualName, actualJob;
	int actualStatusCode;
	
	@Given("I have Url, Method, Requestbody Update Api")
	public void i_have_url_method_requestbody_update_api(List<Map<String, String>> requestData) {
		for (Map<String, String> data : requestData) {
			url = data.get("Url");
			method = data.get("Method");
			requestBodyFileName = data.get("RequestBodyFileName");
		}
	}

	@When("I send request body Update Api")
	public void i_send_request_body_update_api() {
		
		JsonUtils jsonUtils = new JsonUtils();
		
		String projectPath = System.getProperty("user.dir");
		File sourceFile = new File(projectPath + "/src/main/resources/UpdateApi/" + requestBodyFileName);
		File destinationFile = new File(projectPath + "/src/main/resources/UpdateApi/" + "copy" + requestBodyFileName);
		System.out.print(destinationFile);
		
		jsonUtils.copyJsonFile(sourceFile, destinationFile);
		
		String requestBodyString;
		
		try {
			requestBodyString = Files.readString(destinationFile.toPath());
			ApiUtils util = new ApiUtils();
			response = util.sendPostRequest(url, requestBodyString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (response != null) {
        actualStatusCode = response.statusCode();
        actualName = jsonUtils.getValueByKey(response.body(),"name"); 
        actualJob = jsonUtils.getValueByKey(response.body(),"job");
		}
	    
	}

	@Then("I verify {string} and {string} and {string}")
	public void i_verify_and_and(String expectedStatus, String expectedName, String expectedJob) {
		expectedStatus="201";
		expectedName="morpheus";
		expectedJob="zion resident";
		assertEquals(actualStatusCode, Integer.parseInt(expectedStatus));
        assertEquals(actualName, expectedName);
        assertEquals(actualJob, expectedJob);
	}
}