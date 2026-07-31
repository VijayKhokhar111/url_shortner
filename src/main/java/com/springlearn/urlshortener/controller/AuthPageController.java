package com.springlearn.urlshortener.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springlearn.urlshortener.service.UrlService;

@Controller
public class AuthPageController {
	
	public AuthPageController(UrlService urlService) {
		this.urlService = urlService;
	}

	private final UrlService urlService;
	
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
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