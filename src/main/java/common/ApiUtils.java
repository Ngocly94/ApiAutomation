package common;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class ApiUtils {

	HttpResponse<String> response;

	// Send GET request
	public HttpResponse<String> sendGetRequest(String url) {

		try {
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET()
					.header("Content-Type", "application/json").build();
			response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Send get request fail");
		}
		return response;
	}

	// Send POST request
	public HttpResponse<String> sendPostRequest(String url, String jsonRequestBody) {

		try {
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(jsonRequestBody))// pass request body
					.build();

			response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
			System.out.println("requestBodyString:" + jsonRequestBody);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Send get request fail");
		}
		return response;
	}
	 private static HttpRequest.BodyPublisher buildFormDataFromMap(Map<Object, Object> data) {
	        var builder = new StringBuilder();
	        for (Map.Entry<Object, Object> entry : data.entrySet()) {
	            if (builder.length() > 0) {
	                builder.append("&");
	            }
	            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
	            builder.append("=");
	            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
	        }
	        System.out.println(builder.toString());
	        return HttpRequest.BodyPublishers.ofString(builder.toString());
	    }
	 public void sendPost(String name, String job) throws IOException, InterruptedException  {

	        // form parameters
	        Map<Object, Object> data = new HashMap<>();
	        data.put("name", name);
	        data.put("job", job);
	        data.put("ts", System.currentTimeMillis());

	        HttpRequest request = HttpRequest.newBuilder()
	                .POST(buildFormDataFromMap(data))
	                .uri(URI.create("https://reqres.in/api/create"))
	                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
	                .header("Content-Type", "application/x-www-form-urlencoded")
	                .build();

	        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

	        // print status code
	     //   System.out.println(response.statusCode());

	        // print response body
	     //   System.out.println(response.body());

	    }
	 private final HttpClient httpClient = HttpClient.newBuilder()
	            .version(HttpClient.Version.HTTP_2)
	            .build();
	 

}
