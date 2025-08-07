package com.example.Student.Handler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.Student.response.responsegenerator;

public class Exceptional {
	@ControllerAdvice
	public class ExceptionHandling {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e){
		return responsegenerator.errorResponse(e.getMessage());
		
	}
	}

}
