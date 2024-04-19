package com.apex.samples.test;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

import com.apex.samples.core.ApexBaseTest;
import com.apex.samples.core.ApexHttpUtil;
import com.apex.samples.core.ApexHttpValidator;

public class UserApiDELETE_test extends ApexBaseTest implements IUserConstants {
	@Test
	public  static void deleteUser_SuccessData() throws ClientProtocolException, IOException
	{
		String deleteUserId="137";
		HttpResponse response = ApexHttpUtil.sendDeleteRequest(CURRENT_URL, deleteUserId, TOKEN);
		//validate response.
		ApexHttpValidator.performCommonValidations(response, STATUS_CODE_204, STATUS_MSG_NOCONTENT);
		System.out.println(response);
		System.out.println("Delete Executed Successfully");
	}
}
