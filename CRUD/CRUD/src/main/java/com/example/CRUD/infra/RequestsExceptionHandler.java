package com.example.CRUD.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class RequestsExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity threat404() {
		var response = new ExceptionDto("Data not found with provided ID", 404);
		return ResponseEntity.badRequest().body(response);
	}
	
}
