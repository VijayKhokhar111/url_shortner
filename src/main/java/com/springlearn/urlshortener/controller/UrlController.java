package com.springlearn.urlshortener.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springlearn.urlshortener.service.UrlService;

@RestController
public class UrlController {
	
	
	private final UrlService urlService;
	
	
	public UrlController(UrlService urlService) {
		this.urlService = urlService;
	}


	@GetMapping("/shortenUrl/{longUrl}")
	public String shortenURL(@PathVariable String longUrl) {
		return urlService.shortenUrl(longUrl);
	} 
	
	@PostMapping("/req/signup")
    public String registerUser(
            @RequestParam String name,
            @RequestParam String username,
            @RequestParam String password,
            RedirectAttributes redirectAttributes
    ) {
        try {
            urlService.registerUser(
                    name,
                    username,
                    password
            );

            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Account created. You can now log in."
            );

            return "redirect:/login";

        } catch (IllegalArgumentException exception) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    exception.getMessage()
            );

            return "redirect:/signup";
        }
    }
}
