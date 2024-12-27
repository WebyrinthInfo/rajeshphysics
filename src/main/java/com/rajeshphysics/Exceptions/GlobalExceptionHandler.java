package com.rajeshphysics.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rajeshphysics.Payloads.ApiResponse;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	 @ExceptionHandler(ResourceNotFoundException.class)
	 public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
	     ApiResponse response = new ApiResponse();
	     response.setMsg(ex.getMessage());
	     response.setStatus(HttpStatus.NOT_FOUND.toString());
	     response.setTimestamp(LocalDateTime.now());	 
	     return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	 }
	 
	 @ExceptionHandler(ResourceAlreadyExistsException.class)
	 public ResponseEntity<ApiResponse> resourceAlreadyExistsExceptionHandler(ResourceAlreadyExistsException ex) {
	     ApiResponse response = new ApiResponse();
	     response.setMsg(ex.getMessage());
	     response.setStatus(HttpStatus.FOUND.toString());
	     response.setTimestamp(LocalDateTime.now());	
	     return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	 }
	 
	 @ExceptionHandler(ForbbidonExceptions.class)
	 public ResponseEntity<ApiResponse> forbbidonExceptionsHandler(ForbbidonExceptions ex) {
	     ApiResponse response = new ApiResponse();
	     response.setMsg(ex.getMessage());
	     response.setStatus(HttpStatus.FORBIDDEN.toString());
	     response.setTimestamp(LocalDateTime.now());	
	     return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	 }
	 
	 @ExceptionHandler(AuthenticationException.class)
	 public ResponseEntity<ApiResponse> authenticationExceptionHandler(AuthenticationException ex) {
	     ApiResponse response = new ApiResponse();
	     response.setMsg(ex.getMessage());
	     response.setStatus(HttpStatus.FORBIDDEN.toString());
	     response.setTimestamp(LocalDateTime.now());	
	     return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	 }

}
