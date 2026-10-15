package com.springlearn.urlshortener.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springlearn.urlshortener.entity.UrlEntity;

public interface UrlRepository extends JpaRepository<UrlEntity, Long>{
	
	Optional<UrlEntity> findByShortCode(String shortCode);
}
