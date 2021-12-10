package com.dgsl.restapi.testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.dgsl.restapi.util.Constants;

public class TestBase {

	public int RESPONSE_STATUS_200_OK = 200;
	public int RESPONSE_STATUS_201_CREATED = 201;
	public int RESPONSE_STATUS_204_NO_CONTENT = 204;
	public int RESPONSE_STATUS_400_BAD_REQUEST = 400;
	public int RESPONSE_STATUS_401_UNAUTHORISED = 401;
	public int RESPONSE_STATUS_403_FORBIDDEN = 403;
	public int RESPONSE_STATUS_404_NOT_FOUND = 404;
	public int RESPONSE_STATUS_500_INTERNAL_SERVER_ERROR = 500;
	public int RESPONSE_STATUS_502_BAD_GATEWAY = 502;
	public int RESPONSE_STATUS_503_SERVICE_UNAVAILABLE = 503;
	public int RESPONSE_STATUS_504_GATEWAY_TIMEOUT = 504;

	File file;
	protected static Properties prop;
	static FileInputStream inputStream;

	public TestBase() {
		file = new File(Constants.CONFIG_FILE);
		try {
			prop = new Properties();
			inputStream = new FileInputStream(Constants.CONFIG_FILE);
			prop.load(inputStream);
		} catch (FileNotFoundException a) {
			a.printStackTrace();
		} catch (IOException b) {
			b.printStackTrace();
		}
	}

}
