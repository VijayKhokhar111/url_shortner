package com.springlearn.urlshortener.exception;

import java.io.Serial;

public class ShortUrlNotFoundException extends RuntimeException{
	@Serial
	private static final long serialVersionUID = 1L;
	private final String shortCode;
	
	public ShortUrlNotFoundException(String shortCode) {
		 super("Short URL not found: " + shortCode);
		this.shortCode = shortCode;
	}
	
	public String getShortCode() {
		return shortCode;
	}
}
