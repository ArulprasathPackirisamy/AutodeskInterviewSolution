package com.beta.replyservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(ResponseStatusException.class)
	  public ResponseEntity<ErrorMessage> handleInvalidRequestException(ResponseStatusException rse) {
	    return new ResponseEntity<>(
	        new ErrorMessage(rse.getStatus().value(), rse.getReason()),
	        rse.getStatus());
	  }
}
