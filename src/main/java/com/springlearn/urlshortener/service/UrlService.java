package com.springlearn.urlshortener.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.springlearn.urlshortener.entity.AppUser;

public interface UrlService extends UserDetailsService{
	
	String shortenUrl(String url);
	UserDetails loadUserByUsername(String username);
	AppUser registerUser(
            String name,
            String username,
            String rawPassword
    );
}
