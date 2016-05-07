/**
 * 
 */
package com.epam.hasznaltauto;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.epam.hasznaltauto.model.Advertisement;

/**
 * @author G�bor
 *
 */
public class HttpGetIssuer {
	private final String USER_AGENT = "Chrome/50.0.2661.94";

	// HTTP GET request
	public List<Advertisement> sendGet() {
		List<Advertisement> advertisements = new ArrayList<Advertisement>();
		String[] urls = { "http://www.google.hu/", "http://www.fakebook.hu/" };

		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		try (CloseableHttpClient httpClient = httpClientBuilder.build()) {
			for (String url : urls) {

				HttpGet request = new HttpGet(url);

				// add request header
				request.addHeader("User-Agent", USER_AGENT);
				boolean statusOK;
				try {
					HttpResponse response = httpClient.execute(request);
					statusOK = response.getStatusLine().getStatusCode() == HttpStatus.SC_OK;
				} catch(UnknownHostException uhe) {
					statusOK = false;
					uhe.printStackTrace();
				}
				advertisements.add(new Advertisement(url, statusOK));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return advertisements;
	}
}
