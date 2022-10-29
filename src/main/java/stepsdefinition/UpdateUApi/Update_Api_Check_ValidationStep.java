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

public class Update_Api_Check_ValidationStep {
	String url, method, requestBodyFileName;
	HttpResponse<String> response;
	int actualStatusCode;

	@Given("I have Url, Method, Requestbody Update Api_Validation Single field")
	public void i_have_url_method_requestbody_update_api_validation_singer_field(
			List<Map<String, String>> requestData) {
		for (Map<String, String> data : requestData) {
			url = data.get("Url");
			method = data.get("Method");
			requestBodyFileName = data.get("RequestBodyFileName");
		}
	}

	@When("I send request body Update Api with {string} and {string}")
	public void i_send_request_body_update_api_with_and(String fieldname, String value) {
		JsonUtils jsonUtils = new JsonUtils();

		String projectPath = System.getProperty("user.dir");
		File sourceFile = new File(projectPath + "/src/main/resources/UpdateApi/" + requestBodyFileName);
		File destinationFile = new File(projectPath + "/src/main/resources/UpdateApi/" + "copy" + requestBodyFileName);

		jsonUtils.copyJsonFile(sourceFile, destinationFile);

		String requestBodyString = jsonUtils.changeValueBySingleFieldName(destinationFile, fieldname, value);
		System.out.print("res  " + requestBodyString);

		try {
			requestBodyString = Files.readString(destinationFile.toPath());
			ApiUtils util = new ApiUtils();
			response = util.sendPostRequest(url, requestBodyString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (response != null) {
			actualStatusCode = response.statusCode();

		}

	}

	@Then("I verify {int}  Update Api")
	public void i_verify_and_update_api(int expectedStatusCode) {
		assertEquals(actualStatusCode, expectedStatusCode);
	}

}