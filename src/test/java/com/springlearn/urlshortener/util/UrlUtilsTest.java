package com.springlearn.urlshortener.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
public class UrlUtilsTest {
	
	//@Autowired
	//private UrlUtil urlUtil;
	private UrlUtil urlUtil = new UrlUtil();
	
	@Test
	 void test_isValid() {
		assertFalse(urlUtil.isValid("isValid test data"));
		assertFalse(urlUtil.isValid("httpisValid test data"));
		assertTrue(urlUtil.isValid("http://facebook.com"));
		assertFalse(urlUtil.isValid("htt://"));
		assertTrue(urlUtil.isValid("https://facebook.com"));
		assertFalse(urlUtil.isValid(null));
	}
}
