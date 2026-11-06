package com.springlearn.urlshortener.controller.web;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springlearn.urlshortener.service.UrlService;

@RestController
public class RedirectController {

	private final UrlService urlService;

	public RedirectController(UrlService urlService) {
		this.urlService = urlService;
	}

	@GetMapping("/{shortCode:[A-Za-z0-9]{8}}")
	public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
		URI redirectionUri = urlService.getRedirectionUriByShortCode(shortCode);			
		return ResponseEntity.status(HttpStatus.FOUND)
				.location(redirectionUri)
				.build();
	}
}
