package com.springlearn.urlshortener.dto;

public class ShortenUrlResponseDto {
	public ShortenUrlResponseDto(String shortUrl) {
		super();
		this.shortUrl = shortUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	private String shortUrl;
}
