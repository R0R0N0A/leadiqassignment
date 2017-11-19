package com.leadiq.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;

@ControllerAdvice  
@RestController
@Description("A simple handler to return internal server error incase of an exception")
public class GlobalExceptionHandler {
	  
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  
    @ExceptionHandler(value = Exception.class)  
    public String handleException(Exception e) {
		return e.getMessage();
	}
}
