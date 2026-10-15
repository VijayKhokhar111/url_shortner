package com.springlearn.urlshortener.util;

import java.net.URI;

import org.springframework.stereotype.Component;

import com.springlearn.urlshortener.dto.ShortenUrlRequestDto;

@Component
public class UrlUtil {

	public UrlUtil() {
	}

	public boolean isValid(String url) {
		//validate url
		try {
			URI.create(url).toURL();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
