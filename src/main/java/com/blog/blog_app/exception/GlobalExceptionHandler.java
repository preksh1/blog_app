package com.blog.blog_app.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;



@RestControllerAdvice
public class GlobalExceptionHandler {
@ExceptionHandler(NoDataFoundException.class)
public ResponseEntity<Map<String, String>> noDataFoundExceptionHandler(NoDataFoundException ex){
	String message = ex.getMessage();
	Map<String, String> response = new HashMap<>();
	
    response.put("message", message);
    
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
}

@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException ex){
	Map<String, String> response = new HashMap<>();
	
	ex.getBindingResult().getAllErrors().forEach(
			(error) -> {
				String fieldName = ((FieldError) error).getField();
				String message = error.getDefaultMessage();
				response.put(fieldName, message);
			}
			
			);
	
    //response.put("message", message);
    
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
}

}
