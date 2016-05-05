/**
 * 
 */
package com.epam.hasznaltauto;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * @author Gábor
 *
 */
public class HttpGetIssuer {
	private final String USER_AGENT = "Chrome/50.0.2661.94";

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		HttpGetIssuer httpGetIssuer = new HttpGetIssuer();
		httpGetIssuer.sendGet();
	}

	// HTTP GET request
	private void sendGet() {

		String url = "http://www.google.hu/";

		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		try (CloseableHttpClient httpClient = httpClientBuilder.build()) {
			HttpGet request = new HttpGet(url);

			// add request header
			request.addHeader("User-Agent", USER_AGENT);

			HttpResponse response = httpClient.execute(request);

			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response OK : " + (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
