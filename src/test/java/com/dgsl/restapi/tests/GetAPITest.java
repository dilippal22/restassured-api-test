package com.dgsl.restapi.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.dgsl.restapi.restclient.RestClient;
import com.dgsl.restapi.testbase.TestBase;
import com.dgsl.restapi.util.TestUtil;

public class GetAPITest extends TestBase {

	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpRespone;

	Logger log = Logger.getLogger(GetAPITest.class);

	@BeforeTest
	public void setExtent() {
		TestUtil.setExtentReport();
	}

	@BeforeMethod
	public void setUp() {
		testBase = new TestBase();
		serviceUrl = prop.getProperty("ServiceURL");
		apiUrl = prop.getProperty("APIURL");

		url = serviceUrl + apiUrl;

	}

	@Test(priority = 1)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException {
		TestUtil.extentTest = TestUtil.report.startTest("getAPITestWithoutHeaders");

		restClient = new RestClient();
		closeableHttpRespone = restClient.getRequest(url);

		// To get response status code
		int getStatusCode = closeableHttpRespone.getStatusLine().getStatusCode();
		log.info("GET request status code is: " + getStatusCode);

		Assert.assertEquals(getStatusCode, RESPONSE_STATUS_200_OK, "Status code is not 200");

		// To convert response into string
		String responsString = EntityUtils.toString(closeableHttpRespone.getEntity(), "UTF-8");

		// To format string into Json
		JSONObject responseJsonObject = new JSONObject(responsString);
		log.info("Respnse JSON Object is: " + responseJsonObject);

		String perPageValue = TestUtil.getValueByJsonPath(responseJsonObject, "/per_page");
		log.info("Response per page value is: " + perPageValue);
		// Asserting per page value
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);

		String totalPageValue = TestUtil.getValueByJsonPath(responseJsonObject, "/total");
		log.info("Total page value is: " + totalPageValue);
		// Asserting total page value
		Assert.assertEquals(Integer.parseInt(totalPageValue), 12);

		// Get the value from JSON array
		String lastName = TestUtil.getValueByJsonPath(responseJsonObject, "/data[0]/last_name");
		String id = TestUtil.getValueByJsonPath(responseJsonObject, "/data[0]/id");
		String avatar = TestUtil.getValueByJsonPath(responseJsonObject, "/data[0]/avatar");
		String firstName = TestUtil.getValueByJsonPath(responseJsonObject, "/data[0]/first_name");

		log.info(lastName);
		log.info(id);
		log.info(avatar);
		log.info(firstName);

		// To verify response header
		Header[] headerArray = closeableHttpRespone.getAllHeaders();

		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());

		}
		log.info("Headers Array: " + allHeaders);

	}

	@Test(priority = 2)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
		TestUtil.extentTest = TestUtil.report.startTest("getAPITestWithHeaders");
		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("content-type", "application/json");
		headerMap.put("username", "test");
		headerMap.put("password", "test123");
		headerMap.put("Auth Token", "213XSds32");

		closeableHttpRespone = restClient.getRequest(url, headerMap);

		// To get response status code
		int getStatusCode = closeableHttpRespone.getStatusLine().getStatusCode();
		log.info("GET request status code is: " + getStatusCode);

		Assert.assertEquals(getStatusCode, RESPONSE_STATUS_200_OK, "Status code is not 200");

		// To convert response into string
		String responsString = EntityUtils.toString(closeableHttpRespone.getEntity(), "UTF-8");

		// To format string into Json
		JSONObject responseJsonObject = new JSONObject(responsString);
		log.info("Respnse JSON Object is: " + responseJsonObject);

		String perPageValue = TestUtil.getValueByJsonPath(responseJsonObject, "/per_page");
		log.info("Response per page value is: " + perPageValue);
		// Asserting per page value
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);

		String totalPageValue = TestUtil.getValueByJsonPath(responseJsonObject, "/total");
		log.info("Total page value is: " + totalPageValue);
		// Asserting total page value
		Assert.assertEquals(Integer.parseInt(totalPageValue), 12);

		// Get the value from JSON array
		String lastName = TestUtil.getValueByJsonPath(responseJsonObject, "/data[0]/last_name");
		String id = TestUtil.getValueByJsonPath(responseJsonObject, "/data[0]/id");
		String avatar = TestUtil.getValueByJsonPath(responseJsonObject, "/data[0]/avatar");
		String firstName = TestUtil.getValueByJsonPath(responseJsonObject, "/data[0]/first_name");

		log.info("lastName is: " + lastName);
		log.info("id is: " + id);
		log.info("avatar is: " + avatar);
		log.info("firstName is: " + firstName);

		// To verify response header
		Header[] headerArray = closeableHttpRespone.getAllHeaders();

		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());

		}
		log.info("Headers Array: " + allHeaders);

	}

	@AfterMethod
	public void setTestResult(ITestResult result) throws IOException {
		TestUtil.logTestStatus(result);
	}

	@AfterTest
	public void endExtent() {
		TestUtil.endExtentReport();
	}

}
