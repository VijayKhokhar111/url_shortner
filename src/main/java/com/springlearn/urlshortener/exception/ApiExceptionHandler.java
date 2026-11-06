package com.springlearn.urlshortener.exception;

import java.net.URI;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice(basePackages = "com.springlearn.urlshortener.controller.api")
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);
    private static final String TIMESTAMP = "timestamp";

    @ExceptionHandler(ShortUrlNotFoundException.class)
    public ProblemDetail handleShortUrlNotFound(ShortUrlNotFoundException ex, HttpServletRequest request) {
    		log.debug("Short URL not found: code={}, path={}", ex.getShortCode(), request.getRequestURI());
        return buildProblem(HttpStatus.NOT_FOUND, "Short URL not found", ex.getMessage(), request);
    }

    @ExceptionHandler(InvalidUrlException.class)
    public ProblemDetail handleInvalidUrl(InvalidUrlException ex, HttpServletRequest request) {
        log.debug("Invalid URL rejected: path={}", request.getRequestURI());
        return buildProblem(HttpStatus.BAD_REQUEST, "Invalid URL", ex.getMessage(), request);
    }

    @ExceptionHandler(Exception.class)                    // the safety net
    public ProblemDetail handleUnexpected(Exception ex, HttpServletRequest request) {
    	 log.error("Unexpected error on {} {}", request.getMethod(), request.getRequestURI(), ex);
         return buildProblem(
                 HttpStatus.INTERNAL_SERVER_ERROR,
                 "Internal Server Error",
                 "An unexpected error occurred. Please try again later.",
                 request);
    }
    
 // Add timestamp to Spring's built-in errors too (405, 415, malformed JSON, validation...)
    @Override
    protected ResponseEntity<Object> createResponseEntity(
            Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        if (body instanceof ProblemDetail problemDetail) {
            problemDetail.setProperty(TIMESTAMP, Instant.now());
        }
        return super.createResponseEntity(body, headers, statusCode, request);
    }
 //Single place that defines error format
    private ProblemDetail buildProblem(HttpStatus status, String title, String detail, HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, detail);
        problem.setTitle(title);
        problem.setInstance(URI.create(request.getRequestURI()));
        problem.setProperty(TIMESTAMP, Instant.now());
        return problem;
    }
}
