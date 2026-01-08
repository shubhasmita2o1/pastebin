package com.shubhasmita.pastebin.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.shubhasmita.pastebin.entity.Paste;
import com.shubhasmita.pastebin.exception.PasteNotFoundException;
import com.shubhasmita.pastebin.repository.PasteRepository;

@Service
public class PasteService {
	
	private final PasteRepository pasteRepo;
	
	public PasteService(PasteRepository pasteRepo) {
		this.pasteRepo = pasteRepo;
	}
	
	
	public Paste createPaste(String content) {
		
		Paste paste = new Paste();
		paste.setContent(content);
		
		String shortcode = generateShortCode();
		paste.setShortCode(shortcode);
		
		paste.setExpiresAt(LocalDateTime.now().plusHours(48));
		
		return pasteRepo.save(paste);
	}
	
	private String generateShortCode() {
		return UUID.randomUUID()
					.toString()
					.substring(0,6);
	}
	
	
	public Paste getPasteByShortCode(String shortCode) {

	    Paste paste = pasteRepo.findByShortCode(shortCode)
	            .orElseThrow(() ->
	                    new PasteNotFoundException("Paste not found")
	            );

	    if (paste.getExpiresAt() != null &&
	        paste.getExpiresAt().isBefore(LocalDateTime.now())) {
	        throw new PasteNotFoundException("Paste has been expired");
	    }

	    return paste;
	}

}
