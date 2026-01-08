package com.shubhasmita.pastebin.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pastebin")
public class Paste {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@Column(name = "content", nullable = false)
	private String content;
	
	@Column(name = "shortcode", nullable = false, unique = true)
	private String shortCode;
	
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	@Column(name ="expires_at")
	private LocalDateTime expiresAt;
	
	public Paste(){
//		constructor
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
	}
	
	public Long getId() {
		return id;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getShortCode() {
		return shortCode;
	}
	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}
	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}
	
	
}
	