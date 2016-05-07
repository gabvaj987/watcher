package com.epam.hasznaltauto.model;

public class Advertisement {
	private final String url;
	private final Boolean responds;
	
	public Advertisement(String url, Boolean responds) {
		this.url = url;
		this.responds = responds;
	}
	public String getUrl() {
		return url;
	}
	public Boolean getResponds() {
		return responds;
	}
}
