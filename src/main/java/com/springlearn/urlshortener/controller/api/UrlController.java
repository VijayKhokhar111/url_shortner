package com.springlearn.urlshortener.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springlearn.urlshortener.dto.ShortenUrlRequestDto;
import com.springlearn.urlshortener.dto.ShortenUrlResponseDto;
import com.springlearn.urlshortener.service.UrlService;

@RestController
@RequestMapping("/api")
public class UrlController {
	private final UrlService urlService;
	
	public UrlController(UrlService urlService) {
		this.urlService = urlService;
	}
 
	@PostMapping
	public ResponseEntity<ShortenUrlResponseDto> shortenURL(@RequestBody ShortenUrlRequestDto requestDto){
		ShortenUrlResponseDto responseDto = urlService.shortenUrl(requestDto);
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(responseDto);
	} 
	
	@GetMapping("/{userId}/{urlId}")
	public ResponseEntity<Object> getShortUrlById(@PathVariable Long urlId){
		return null;
	} 
	
	@GetMapping("/shortUrls/{userId}")
	public ResponseEntity<Object> getShortUrlsByUserId(@PathVariable Long userId){
		return null;
	} 
}
