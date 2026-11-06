package com.springlearn.urlshortener.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = "com.springlearn.urlshortener.controller.web")
public class WebExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(WebExceptionHandler.class);

	@ExceptionHandler(ShortUrlNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleShortUrlNotFound(ShortUrlNotFoundException ex, Model model) {
        log.debug("Redirect failed, unknown short code: {}", ex.getShortCode());
        model.addAttribute("shortCode", ex.getShortCode());
        return "error/link-not-found";
    }
}
