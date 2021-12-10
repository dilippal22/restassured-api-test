package com.dgsl.restapi.restclient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {

	// GET method without headers
	public CloseableHttpResponse getRequest(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse closeableHttpRespone = httpClient.execute(httpGet);
		return closeableHttpRespone;

	}

	// GET method with headers
	public CloseableHttpResponse getRequest(String url, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);

		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableHttpRespone = httpClient.execute(httpGet);
		return closeableHttpRespone;

	}

	// POST method
	public CloseableHttpResponse postRequest(String url, String entityString, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new StringEntity(entityString)); // For POST request payload

		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
		return closeableHttpResponse;
	}

}
