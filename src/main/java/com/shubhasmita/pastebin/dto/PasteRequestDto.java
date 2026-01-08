package com.shubhasmita.pastebin.dto;

import jakarta.validation.constraints.NotBlank;


public class PasteRequestDto {
	
	@NotBlank(message = "Original Message cannot be empty")
	private String content;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
