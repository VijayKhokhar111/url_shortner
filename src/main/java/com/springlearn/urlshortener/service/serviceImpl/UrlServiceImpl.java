package com.springlearn.urlshortener.service.serviceImpl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springlearn.urlshortener.dto.ShortenUrlRequestDto;
import com.springlearn.urlshortener.dto.ShortenUrlResponseDto;
import com.springlearn.urlshortener.entity.AppUser;
import com.springlearn.urlshortener.entity.UrlEntity;
import com.springlearn.urlshortener.repository.AppUserRepository;
import com.springlearn.urlshortener.repository.UrlRepository;
import com.springlearn.urlshortener.service.UrlService;
import com.springlearn.urlshortener.util.UrlUtil;

@Service
public class UrlServiceImpl implements UrlService{

	AppUserRepository appUserRepository;
	UrlRepository urlRepository;
	
	private final PasswordEncoder passwordEncoder;
	private final UrlUtil urlUtil;
	
	 public UrlServiceImpl(AppUserRepository appUserRepository, UrlRepository urlRepository, PasswordEncoder passwordEncoder, UrlUtil urlUtil) {
	        this.appUserRepository = appUserRepository;
	        this.passwordEncoder = passwordEncoder;
	        this.urlUtil = urlUtil;
	        this.urlRepository = urlRepository;
	    }

	@Override
	public ShortenUrlResponseDto shortenUrl(ShortenUrlRequestDto requestDto) {
		//validate url
		boolean isValid = urlUtil.isValid(requestDto);
		
		if(!isValid) {
			throw new RuntimeException("Url is invalid");
		}
		//create short url
		String shortenUrl = "shorten Url";
		UrlEntity urlEntity = new UrlEntity();
		urlEntity.setMainUrl(requestDto.getUrl());
		urlEntity.setShortenUrl(shortenUrl);
		
		//persist to database
		urlEntity = urlRepository.save(urlEntity);
		
		//return meaningful data
		ShortenUrlResponseDto responseDto = new ShortenUrlResponseDto(urlEntity.getShortenUrl());
		return responseDto;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) {
	    AppUser appUser = appUserRepository.findByUsername(username)
	            .orElseThrow(() ->
	                    new UsernameNotFoundException(
	                            "User not found: " + username
	                    )
	            );

	    return User.withUsername(appUser.getUsername())
	            .password(appUser.getPassword())
	            .roles("USER")
	            .build();
	}


	@Override
	public AppUser registerUser(String name, String username, String rawPassword) {
		 String normalizedUsername = username.trim().toLowerCase();
		 
		 if (appUserRepository.existsByUsername(normalizedUsername)) {
	            throw new IllegalArgumentException(
	                    "Username already exists"
	            );
	        }
		 
		 AppUser appUser = new AppUser();
	        appUser.setName(name.trim());
	        appUser.setEmail(normalizedUsername);

	        appUser.setPassword(
	                passwordEncoder.encode(rawPassword)
	        );

	        return appUserRepository.save(appUser);
	}

}
