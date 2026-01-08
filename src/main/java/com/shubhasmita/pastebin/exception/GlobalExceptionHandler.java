package com.shubhasmita.pastebin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(PasteNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handlePasteNotFound(PasteNotFoundException ex){
		
		Map<String, Object> error = new HashMap<>();
		error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("error", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationErrors(
	        MethodArgumentNotValidException ex) {

	    Map<String, String> errors = new HashMap<>();

	    ex.getBindingResult().getFieldErrors().forEach(error ->
	            errors.put(error.getField(), error.getDefaultMessage())
	    );

	    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {

	    Map<String, Object> error = new HashMap<>();
	    error.put("timestamp", LocalDateTime.now());
	    error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
	    error.put("error", "Internal server error");
	    error.put("message", ex.getMessage());

	    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
