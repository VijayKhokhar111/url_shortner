package com.springlearn.urlshortener.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "urls")
public class UrlEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private long userId;

	@Column
	private String redirectionUrl;
	
	@Column(unique = true)
	private String shortCode;
	
	@Column
	private Instant createdAt;
	
	
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public UrlEntity() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRedirectionUrl() {
		return redirectionUrl;
	}

	public void setRedirectionUrl(String mainUrl) {
		this.redirectionUrl = mainUrl;
	}

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortenUrl) {
		this.shortCode = shortenUrl;
	}
}
