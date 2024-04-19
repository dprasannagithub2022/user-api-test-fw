package com.apex.samples.core;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class ApexHttpUtil {
	
	public static HttpResponse sendPostRequest(String url, String token, String name, String email, String gender,
			String status) throws ClientProtocolException, IOException {

		HttpClient client = HttpClientBuilder.create().build();
		// String data="{\"id\":1,\"name\":\"Ramya
		// Kutha1\",\"email\":\"Ramya_keuth1@mckenzie-runolfsson.net\",\"gender\":\"male\",\"status\":\"active\"}";
		String data = "{\n" + "    \"name\": \"" + name + "\",\n" + "    \"email\": \"" + email + "\",\n"
				+ "    \"gender\": \"" + gender + "\",\n" + "    \"status\": \"" + status + "\"\n" + "}";
		
		
		
		StringEntity input = new StringEntity(data);

		input.setContentType("application/json");

		HttpPost post = new HttpPost(url);

		post.addHeader("Authorization", token);

		post.setHeader("Accept", "application/json");
		post.setHeader("Content-type", "application/json");
		post.setEntity(input);
		// post.setEntity(new StringEntity(data));
		HttpResponse response = client.execute(post);

		return response;
	}
	


	public static HttpResponse sendUpdateRequest(String currentUrl, String userID, String token)
			throws ClientProtocolException, IOException {

		// userID="4209";

		HttpClient Client = HttpClientBuilder.create().build();
		String data = "{\"id\":" + userID
				+ ",\"name\":\"Kranthi Deepala\",\"email\":\"Kranthi.deepala@mckenzie-runolfsson.net\",\"gender\":\"female\",\"status\":\"active\"}";
		HttpResponse response;
//		 // fetching data by sending user id.
//		 response = sendGetRequest(CURRENT_URL, userID, TOKEN);
//		 
//		 String result = getResponse(response);
//		 //here I need to update data in result.

		HttpPut putrequest = new HttpPut(currentUrl + "/" + userID);
		putrequest.setHeader("Accept", "application/json");
		putrequest.setHeader("Content-type", "application/json");

		StringEntity stringEntity = new StringEntity(data);
		putrequest.setEntity(stringEntity);
		putrequest.addHeader("Authorization", token);
		System.out.println("Executing request " + putrequest.getRequestLine());
		response = Client.execute(putrequest);
		return response;

	}
	
	

	public static HttpResponse sendDeleteRequest(String url, String userid, String token)
			throws ClientProtocolException, IOException {

		HttpClient Client = HttpClientBuilder.create().build();
		HttpDelete DeleteRequest = new HttpDelete(url + "/" + userid);
		HttpResponse response;

		DeleteRequest.addHeader("Authorization", token);

		response = Client.execute(DeleteRequest);
		return response;

//	 System.out.println(response.getStatusLine().getStatusCode());
//	 System.out.println(response.getStatusLine().getReasonPhrase());

	}

	public static String getResponse(HttpResponse response) throws IOException {
		HttpEntity entity = response.getEntity();
		String result = "";
		if (entity != null) {
			// return it as a string.
			result = EntityUtils.toString(entity);

		}
		System.out.println(result);
		return result;
	}

	public static HttpResponse sendGetRequest(String url, String token)
			throws IOException, ClientProtocolException {
		HttpClient Client = HttpClientBuilder.create().build();
		HttpGet GetRequest = new HttpGet(url);
				//+ "/" + userid);

		GetRequest.addHeader("Authorization", token);

		HttpResponse response;

		response = Client.execute(GetRequest);
		return response;
	}

}
