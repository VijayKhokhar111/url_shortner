package com.springlearn.urlshortener.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
	
	@GetMapping("/{shortCode}")
	public ResponseEntity<Void> getRedirectionUri(@PathVariable String shortCode) {
		try {
			URI redirectionUri = urlService.getRedirectionUriByShortCode(shortCode);
			if (redirectionUri == null) return null;
			
			return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY.value())
					.location(redirectionUri)
					.build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
 
	@PostMapping
	public ResponseEntity<Object> shortenURL(@RequestBody ShortenUrlRequestDto requestDto){
		ShortenUrlResponseDto responseDto;
		try {
			responseDto = urlService.shortenUrl(requestDto);
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(responseDto);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
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
