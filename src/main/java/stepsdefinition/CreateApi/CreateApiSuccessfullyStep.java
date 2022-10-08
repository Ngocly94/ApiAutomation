package stepsdefinition.CreateApi;

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

public class CreateApiSuccessfullyStep {
	String url, method,name, job;
	int expectedStatus;
	HttpResponse<String> response;
	JsonUtils jsonUtils = new JsonUtils();

	@Given("I have {string} and {string} and {string} and {string}")
	public void i_have_url_and_method_and_request_body(String inputUrl, String inputMethod, String inputName, String inputJob) {
//		for (Map<String, String> data : requestData) {
			url = inputUrl;
			method = inputMethod;
			job = inputJob;
			name = inputName;
//			name = data.get("RequestBodyFileName");
//		}
	}

	@When("I sent request create user")
	public void i_send_request() {
//		String projectPath = System.getProperty("user.dir");
//		File sourceFile = new File(projectPath + "/src/main/resources/LoginApi/" + requestBodyFileName);
//		File destinationFile = new File(projectPath + "/src/main/resources/LoginApi/" + "copy" + requestBodyFileName);
//		System.out.print(destinationFile);
//		jsonUtils.copyJsonFile(sourceFile, destinationFile);
		String requestBodyString;
//		requestBodyString = "{\n\"name\": \""+name+"\",\n\"job\": \""+job +"\"\n}";
		requestBodyString = "{\n"
				+ "    \"name\": \""+name+"\",\n"
				+ "    \"job\": \""+job+"\"\n"
				+ "}";
//		requestBodyString = "{\"name\": \"${}\",\n"
//				+ "			\"job\": \"Tester\"}";
		//			requestBodyString = Files.readString(destinationFile.toPath());
					ApiUtils util = new ApiUtils();
					response = util.sendPostRequest(url, requestBodyString);

	}

	@Then("I verify the {int}")
	public void response_return_status_code_and_token(int expectedStatusCode) {
		int actualStatusCode=0;
		if (response != null) {
        actualStatusCode = response.statusCode();
		}
		System.out.println("actual status code:" + actualStatusCode);
		assertEquals(actualStatusCode, expectedStatusCode);
        
	}
}
