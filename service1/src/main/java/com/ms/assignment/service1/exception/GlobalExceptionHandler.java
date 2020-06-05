package com.ms.assignment.service1.exception;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.google.common.net.HttpHeaders;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({RuntimeException.class})
	public ResponseEntity<String> handleException(Exception ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}


	@ExceptionHandler({ConstraintViolationException.class}) 
	public ResponseEntity<String> ConstraintViolationException(Exception ex){ 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()); 
	}

	
//	  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders
//			  headers, HttpStatus status, WebRequest request) {
//	  
//	  Map<String, Object> body = new LinkedHashMap<>(); 
//	  body.put("timestamp", new Date()); 
//	  body.put("status", status.value());
//	  
//	  //Get all errors 
//	  List<String> errors = ex.getBindingResult()
//			  				.getFieldErrors() 
//			  				.stream() 
//			  				.map(x -> x.getDefaultMessage())
//			  				.collect(Collectors.toList());
//	  
//	  body.put("errors", errors); 
//	  return new ResponseEntity<>(body, headers, status); 
//	  
//	  }
	 
}
