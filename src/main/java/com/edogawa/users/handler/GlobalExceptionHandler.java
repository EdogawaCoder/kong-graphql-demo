package com.edogawa.users.handler;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.edogawa.users.exceptions.EmailAlreadyExistsException;
import com.edogawa.users.exceptions.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ProblemDetail handleUserNotFoundException(UserNotFoundException ex) {
		var pd = ProblemDetail.forStatusAndDetail(NOT_FOUND, ex.getMessage());
		pd.setTitle("User Not Found");
		pd.setProperty("field", "email");
		return pd;

	}
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ProblemDetail handleEmailAlreadyExistsException(Exception ex) {
		var pd = ProblemDetail.forStatusAndDetail(CONFLICT, ex.getMessage());
		pd.setTitle("Email Already Exists");
		pd.setProperty("field", "email");
		return pd;

	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ProblemDetail handleInvalidValidation(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();
		
		for (var error : ex.getBindingResult().getFieldErrors()) {
			var fields = (error instanceof FieldError fe) ? fe.getField() : error.getObjectName();
			errors.put(fields, error.getDefaultMessage());
		}
		
		var pd = ProblemDetail.forStatus(BAD_REQUEST);
		pd.setTitle("Validation Error");
		pd.setProperty("errors", errors);
		return pd;
		
	}
}