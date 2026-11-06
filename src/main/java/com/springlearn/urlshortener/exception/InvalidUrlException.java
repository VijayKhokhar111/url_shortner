package com.springlearn.urlshortener.exception;

import java.io.Serial;

public class InvalidUrlException extends RuntimeException {
	 @Serial
	 private static final long serialVersionUID = 1L;

	    public InvalidUrlException() {
	        super("The URL must be a valid http or https address.");
	    }
}
