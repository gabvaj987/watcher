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
		String[] urls = {

				"http://www.hasznaltauto.hu/auto/skoda/octavia/skoda_octavia_1.2_tsi_ambiente_magyar_vez.szkonyv-9725682",
				"http://www.hasznaltauto.hu/auto/skoda/octavia/skoda_octavia_1.2_tsi_clever_maxidot_tempomat_1_tulaj_mo-i-10005193",
				"http://www.hasznaltauto.hu/auto/skoda/octavia/skoda_octavia_1.4_tsi_elegance_ujszeru_szervizkonyv-10136103",
				"http://www.hasznaltauto.hu/auto/skoda/octavia/skoda_octavia_1.2_tsi_clever_86.000-km_magyar_1.tulaj-10000221",
				"http://www.hasznaltauto.hu/auto/skoda/octavia/skoda_octavia_1.4_16v._tour._41098_km-10058319",
				"http://www.hasznaltauto.hu/auto/skoda/octavia/skoda_octavia_1.4_tsi_elegance_mo-i.szervizkonyv.88.000.km-10058716" };

		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		for (String url : urls) {
			try (CloseableHttpClient httpClient = httpClientBuilder.build()) {

				HttpGet request = new HttpGet(url);

				// add request header
				request.addHeader("User-Agent", USER_AGENT);
				boolean statusOK;
				try {
					HttpResponse response = httpClient.execute(request);
					statusOK = response.getStatusLine().getStatusCode() == HttpStatus.SC_OK;
					System.out.println(url);
				} catch (UnknownHostException uhe) {
					statusOK = false;
					uhe.printStackTrace();
				}
				advertisements.add(new Advertisement(url, statusOK));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return advertisements;
	}
}
