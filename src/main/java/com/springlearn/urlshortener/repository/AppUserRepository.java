package com.springlearn.urlshortener.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springlearn.urlshortener.entity.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>{
	
	Optional<AppUser> findByUsername(String username);
	boolean existsByUsername(String username);
}
