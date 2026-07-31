package com.springlearn.urlshortener.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.springlearn.urlshortener.dto.ShortenUrlRequestDto;
import com.springlearn.urlshortener.dto.ShortenUrlResponseDto;
import com.springlearn.urlshortener.entity.AppUser;

public interface UrlService extends UserDetailsService{
	
	ShortenUrlResponseDto shortenUrl(ShortenUrlRequestDto requestDto);
	UserDetails loadUserByUsername(String username);
	AppUser registerUser(
            String name,
            String username,
            String rawPassword
    );
}
