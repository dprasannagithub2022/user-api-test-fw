
package com.apex.samples.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.apex.samples.core.ApexBaseTest;
import com.apex.samples.core.ApexHttpUtil;
import com.apex.samples.core.ApexHttpValidator;
import com.apex.samples.core.ApexXlsUtilities;
import com.apex.samples.core.User;
import com.google.gson.Gson;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

public class UserApiPost_test extends ApexBaseTest implements IUserConstants {

	private List<User> userLst = new ArrayList<User>();

	@DataProvider(name = "dp_postdata")
	public Object[][] postUsers() {
		//String[] data = {"1009","sss","anc@gmial.com","female","active"};
		String[][] data = ApexXlsUtilities.getTableArray(XCEL_FILE_PATH, "user-api-test-sheet", "PostData");
		return data;
	}

	
	@Test
	public void getUserObjectfromJson()
	{
		String data = "{\"id\":" + 1009
				+ ",\"name\":\"Kranthi Deepala\",\"email\":\"Kranthi.deepala@mckenzie-runolfsson.net\",\"gender\":\"female\",\"status\":\"active\"}";
		Gson gson = new Gson();
		// converting json string to java object.

		User user = gson.fromJson(data, User.class);
		
		//conversting User object to json
		String userjson = gson.toJson(user);
				System.out.println("******"+userjson);
	
	}
	@Test(dataProvider = "dp_postdata", priority = 1)
	public void createUsers_post(String id, String name, String email, String gender, String status)
			throws ParseException, IOException {
		String URL = CURRENT_URL;
		System.out.println(URL);
		HttpResponse response = ApexHttpUtil.sendPostRequest(CURRENT_URL, TOKEN, name, email, gender, status);

		String result = ApexHttpUtil.getResponse(response);

		System.out.println(result);
		ApexHttpValidator.performCommonValidations(response, STATUS_CODE_201, STATUS_MSG_CREATED);

		Gson gson = new Gson();
		// converting json string to java object.

		User user = gson.fromJson(result, User.class);
		//String userjson = gson.toJson(user);
		//System.out.println("******"+userjson);
		

		System.out.println(user.getId());
		// adding user data in Users list to create a new users into excel
		
		userLst.add(user);

	}
// to write data into excel file.
	//@Test(priority = 2)
//	public void PostNewUsersIntoExcelFile() throws WriteException, BiffException, IOException {
//		List<List<String>> dataListWithLabels = new ArrayList<>();
//		dataListWithLabels.add(Arrays.asList("id", "name", "email", "gender", "status"));
//		for(User user : userLst) {
//			List<String> userData = Arrays.asList(user.getId(), user.getName(), user.getEmail(), user.getGender(), user.getStatus());
//			dataListWithLabels.add(userData);
//		}
//		ApexXlsUtilities util = new ApexXlsUtilities();
//		util.writeRows("PostData_excel_file.xls", dataListWithLabels, "data", "sheet1");
//	}
//	



}
