package com.springlearn.urlshortener.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springlearn.urlshortener.entity.AppUser;
import com.springlearn.urlshortener.repository.AppUserRepository;
import com.springlearn.urlshortener.service.UrlService;

@Service
public class UrlServiceImpl implements UrlService{

	@Autowired
	AppUserRepository appUserRepository;
	private final PasswordEncoder passwordEncoder;
	
	 public UrlServiceImpl(
	            AppUserRepository appUserRepository,
	            PasswordEncoder passwordEncoder
	    ) {
	        this.appUserRepository = appUserRepository;
	        this.passwordEncoder = passwordEncoder;
	    }

	@Override
	public String shortenUrl(String url) {
		return "shorten Url";
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
