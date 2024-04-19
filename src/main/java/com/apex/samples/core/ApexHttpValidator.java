package com.apex.samples.core;

import org.apache.http.HttpResponse;
import org.testng.Assert;



public class ApexHttpValidator {

	public static void performCommonValidations(HttpResponse response, int statusCode, String statusMsg) {

		Assert.assertEquals(response.getStatusLine().getStatusCode(),statusCode);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(),statusMsg);
		// response.getStatusLine().getStatusCode(), statusCode);

	}
	public static void checkBodyMessage(String result,String expectedString) {
		Assert.assertTrue(result.contains(expectedString));
	}
}
