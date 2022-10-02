package stepsdefinition.LoginApi;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import common.ApiUtils;
import common.JsonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SendLogInAPISuccessfullySteps {

	String url, method, requestBodyFileName;
	HttpResponse<String> response;
	JsonUtils jsonUtils = new JsonUtils();

	@Given("I have url and method and request body")
	public void i_have_url_and_method_and_request_body(List<Map<String, String>> requestData) {
		for (Map<String, String> data : requestData) {
			url = data.get("URL");
			method = data.get("Method");
			requestBodyFileName = data.get("RequestBodyFileName");
		}
	}

	@When("I send request")
	public void i_send_request() {
		String projectPath = System.getProperty("user.dir");
		File sourceFile = new File(projectPath + "/src/main/resources/LoginApi/" + requestBodyFileName);
		File destinationFile = new File(projectPath  + "/src/main/resources/LoginApi/" +"copy"+  requestBodyFileName);
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

	}

	@Then("Response return status code {int} and token {string}")
	public void response_return_status_code_and_token(int expectedStatusCode, String expectedToken) {
		int actualStatusCode=0;
		String actualToken="";
		if (response != null) {
        actualStatusCode = response.statusCode();
        actualToken = jsonUtils.getValueByKey(response.body(),"token");    
		}
		assertEquals(actualStatusCode, expectedStatusCode);
        assertEquals(actualToken, expectedToken);
	}

}
