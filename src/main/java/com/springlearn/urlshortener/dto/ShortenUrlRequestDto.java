package com.springlearn.urlshortener.dto;

public class ShortenUrlRequestDto {
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ShortenUrlRequestDto(String url) {
		this.url = url;
	}

	private String url;
}
