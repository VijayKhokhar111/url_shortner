package com.springlearn.urlshortener.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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


	@GetMapping("/shortenUrl/{url}")
	public ShortenUrlResponseDto shortenURL(@PathVariable String url) {
		ShortenUrlRequestDto requestDto = new ShortenUrlRequestDto(url);
		ShortenUrlResponseDto responseDto = urlService.shortenUrl(requestDto);
		return responseDto;
	} 
	
	@GetMapping("/")
	public String getHomePage() {
		return "logged in as: ";
	} 
}
