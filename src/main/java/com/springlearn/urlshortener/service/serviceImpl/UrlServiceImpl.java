package com.springlearn.urlshortener.service.serviceImpl;

import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.dao.DataIntegrityViolationException;
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
	public ShortenUrlResponseDto shortenUrl(ShortenUrlRequestDto requestDto) throws Exception{
		try {
			//validate url
			boolean isValid = urlUtil.isValid(requestDto.getUrl());
			if(!isValid) {
				throw new RuntimeException("Url is invalid");
			}
			
			//create short url
			UrlEntity urlEntity = createShortUrlCode(requestDto);
			//return meaningful data
			// can also build using @Builder of lombok
			ShortenUrlResponseDto responseDto = new ShortenUrlResponseDto(urlEntity.getShortCode());
			return responseDto;
			
		} catch (DataIntegrityViolationException e) {
			//if shortcode isalready available then only create again
			//create short url
			UrlEntity urlEntity = createShortUrlCode(requestDto);
			
			//return meaningful data
			// can also build using @Builder of lombok
			ShortenUrlResponseDto responseDto = new ShortenUrlResponseDto(urlEntity.getShortCode());
			return responseDto;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private UrlEntity createShortUrlCode(ShortenUrlRequestDto requestDto) throws Exception{
		String shortenUrl = RandomStringUtils.randomAlphanumeric(8);
		UrlEntity urlEntity = new UrlEntity();
		urlEntity.setUserId(1);
		urlEntity.setRedirectionUrl(requestDto.getUrl());
		urlEntity.setShortCode(shortenUrl);
		urlEntity.setCreatedAt(Instant.now());
		//persist to database
		urlEntity = urlRepository.save(urlEntity);
		return urlEntity;
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

	@Override
	public List<ShortenUrlResponseDto> getShortenedUrls() throws Exception {
		 List<UrlEntity> shortenedUrls= urlRepository.findAll();
		 
		 List<ShortenUrlResponseDto> shortenedUrlDtos = shortenedUrls.stream()
		 			.map(url -> 
		 				new ShortenUrlResponseDto(url.getShortCode()))
		 			.toList();
		return shortenedUrlDtos;
	}

	@Override
	public List<ShortenUrlResponseDto> getShortenedUrlById() throws Exception {
		return null;
	}
	
	@Override
	public URI getRedirectionUriByShortCode(String shortCode) throws Exception {
		String urlToBeParsed = urlRepository.findByShortCode(shortCode)
				.map(UrlEntity::getRedirectionUrl)
				.orElse("/");
		return URI.create(urlToBeParsed);
	}
}
