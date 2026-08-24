package com.springlearn.urlshortener.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springlearn.urlshortener.dto.ShortenUrlRequestDto;
import com.springlearn.urlshortener.dto.ShortenUrlResponseDto;
import com.springlearn.urlshortener.service.UrlService;

@RestController
public class UrlController {
	
	
	private final UrlService urlService;
	
	public UrlController(UrlService urlService) {
		this.urlService = urlService;
	}


	@PostMapping("/shortenUrl")
	public ShortenUrlResponseDto shortenURL(@RequestBody ShortenUrlRequestDto requestDto) {
		ShortenUrlResponseDto responseDto = urlService.shortenUrl(requestDto);
		return responseDto;
	} 
}
