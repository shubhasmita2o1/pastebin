package com.shubhasmita.pastebin.dto;

public class PasteResponseDto {
	
	private String shortCode;
	
	public String getShortCode() {
		return shortCode;
	}
	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}
	
	public PasteResponseDto(String shortCode) {
		this.shortCode = shortCode;
	}

}
