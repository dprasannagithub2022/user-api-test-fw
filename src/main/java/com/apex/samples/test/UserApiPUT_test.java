package com.apex.samples.test;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

import com.apex.samples.core.*;

public class UserApiPUT_test extends ApexBaseTest implements IUserConstants {

	@Test
	public void updateUserWithSuccessData() throws ClientProtocolException, IOException {
		String userID = "137";
		//execute update endpoint and get response.
		HttpResponse response = ApexHttpUtil.sendUpdateRequest(CURRENT_URL, userID, TOKEN);

		
		// validate data.

		ApexHttpValidator.performCommonValidations(response, STATUS_CODE_200, STATUS_MSG_OK);

		System.out.println("Update Executed.");
		// String result = getResponse(response);

	}

}
