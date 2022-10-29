package stepsdefinition.RegisterApi;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.ApiUtils;
import common.JsonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Validate_Two_Singer_Field_Register_ApiSteps {
	String url, method, requestBodyFileName;
	int actualStatusCode;
	String actualToken;

	@Given("I have url and method and request body register api")
	public void given_I_Have_Url_Method_RequestBody(List<Map<String, String>> requestData) {
		for (Map<String, String> data : requestData) {
			url = data.get("Url");
			method = data.get("Method");
			requestBodyFileName = data.get("RequestBodyFileName");
		}

	}

	@When("I send request body register with {string} and {string} and {string} and {string}")
	public void when_I_Send_Request(String fieldName1, String value1, String fieldName2, String value2) {
		HashMap<String, String> fieldNameValues = new HashMap<String, String>();
		fieldNameValues.put(fieldName1, value1);
		fieldNameValues.put(fieldName2, value2);
		HttpResponse<String> response = null;
		JsonUtils jsonUtils = new JsonUtils();
		ApiUtils apiUtils = new ApiUtils();
		String projectPath = System.getProperty("user.dir");
		File sourceFile = new File(projectPath + "/src/main/resources/RegisterApi/" + requestBodyFileName);
		File destinationFile = new File(
				projectPath + "/src/main/resources/RegisterApi/" + "copy" + requestBodyFileName);
		System.out.print(destinationFile);
		jsonUtils.copyJsonFile(sourceFile, destinationFile);
		String requestBodyString = jsonUtils.changeValueOfMultiField(fieldNameValues, destinationFile);
		try {
			requestBodyString = Files.readString(destinationFile.toPath());
			response = apiUtils.sendPostRequest(url, requestBodyString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (response != null) {
			actualStatusCode = response.statusCode();
			actualToken = jsonUtils.getValueByKey(response.body(), "token");
		}

	}

	@Then("I verify the {string} and {string} register api")
	public void the_I_Verify_Status_Token(String expectedStatusCode, String expectedToken) {
		assertEquals(actualStatusCode, Integer.parseInt(expectedStatusCode));
		assertEquals(actualToken, expectedToken);

	}

}
