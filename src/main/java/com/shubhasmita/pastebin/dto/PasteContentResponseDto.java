package com.shubhasmita.pastebin.dto;

public class PasteContentResponseDto {

	private String content;

    public PasteContentResponseDto(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
