package com.apex.samples.test;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.apex.samples.core.*;

public class UserApiGET_test extends ApexBaseTest implements IUserConstants {

	@DataProvider(name = "dp_successdata")
	public Object[][] getSuccessfuldata() {
//	String[][] data={
//		{"137","Ms. Anjushree Jha"},
//	 {"140","Shrishti Devar"}
// };

		String[][] data = ApexXlsUtilities.getTableArray(XCEL_FILE_PATH, "user-api-test-sheet", "SuccessData");
		return data;
	}

	@Test(dataProvider="dp_successdata")
	public static void testGetwithSuccessData(String id, String name) throws ParseException, IOException {
		// prepare url
		String URL = CURRENT_URL + "/" + id;
		System.out.println(URL);

		// send get request and get response
		HttpResponse response = ApexHttpUtil.sendGetRequest(URL,TOKEN);

		// sendGetRequest(URL);
		String result = ApexHttpUtil.getResponse(response);
		System.out.println(result);

		// validate response.
		ApexHttpValidator.performCommonValidations(response, STATUS_CODE_200, STATUS_MSG_OK);

	}
	@Test
	public static void testGetwithBlank() throws ParseException, IOException {
		// prepare url
		
		//%12 is for space or blank. I have written this because it is not 
		//accepting space in url. in that case i need to get uri codes for characters and use it.
		String id="%12";
		String URL = CURRENT_URL + "/"+id;
		System.out.println(URL);

		// send get request and get response
		HttpResponse response = ApexHttpUtil.sendGetRequest(URL,TOKEN);

		// sendGetRequest(URL);
		String result = ApexHttpUtil.getResponse(response);
		System.out.println(result);

		// validate response.
		ApexHttpValidator.performCommonValidations(response, STATUS_CODE_404,STATUS_MSG_NOTFOUND);
		
		String message=result.contains("Resource not found")?"Resource not found":"NotFound";
		ApexHttpValidator.checkBodyMessage(message, MESSAGE_RESOURCE_NOTFOUND);	
		
	}


}
