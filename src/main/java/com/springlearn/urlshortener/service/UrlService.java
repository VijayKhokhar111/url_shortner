package com.springlearn.urlshortener.service;

import java.net.URI;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.springlearn.urlshortener.dto.ShortenUrlRequestDto;
import com.springlearn.urlshortener.dto.ShortenUrlResponseDto;
import com.springlearn.urlshortener.entity.AppUser;

public interface UrlService extends UserDetailsService{
	
	ShortenUrlResponseDto shortenUrl(ShortenUrlRequestDto requestDto) throws Exception;
	List<ShortenUrlResponseDto> getShortenedUrls() throws Exception;
	List<ShortenUrlResponseDto> getShortenedUrlById() throws Exception;
	UserDetails loadUserByUsername(String username);
	AppUser registerUser(
            String name,
            String username,
            String rawPassword
    );
	URI getRedirectionUriByShortCode(String shortCode) throws Exception;
}
