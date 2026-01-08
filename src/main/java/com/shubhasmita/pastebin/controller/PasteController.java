package com.shubhasmita.pastebin.controller;

import com.shubhasmita.pastebin.dto.PasteRequestDto;
import com.shubhasmita.pastebin.dto.PasteResponseDto;
import com.shubhasmita.pastebin.dto.PasteContentResponseDto;
import com.shubhasmita.pastebin.entity.Paste;
import com.shubhasmita.pastebin.services.PasteService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paste")
public class PasteController {
	
	private final PasteService pasteService;
	
	public PasteController(PasteService pasteService) {
		this.pasteService = pasteService;
	}
	
	@PostMapping
    public ResponseEntity<PasteResponseDto> createPaste(
            @Valid @RequestBody PasteRequestDto requestDto) {

        Paste paste = pasteService.createPaste(requestDto.getContent());

        PasteResponseDto responseDto =
                new PasteResponseDto(paste.getShortCode());

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
	
	
	@GetMapping("/{shortCode}")
	public ResponseEntity<PasteContentResponseDto> getPaste(
	        @PathVariable String shortCode) {

	    Paste paste = pasteService.getPasteByShortCode(shortCode);

	    PasteContentResponseDto responseDto =
	            new PasteContentResponseDto(paste.getContent());

	    return ResponseEntity.ok(responseDto);
	}


}
