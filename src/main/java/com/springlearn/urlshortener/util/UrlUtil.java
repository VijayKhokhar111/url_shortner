package com.springlearn.urlshortener.util;

import org.springframework.stereotype.Component;

import com.springlearn.urlshortener.dto.ShortenUrlRequestDto;

@Component
public class UrlUtil {

	public UrlUtil() {
	}

	public boolean isValid(ShortenUrlRequestDto requestDto) {
		//validate url
		return true;
	}
}
