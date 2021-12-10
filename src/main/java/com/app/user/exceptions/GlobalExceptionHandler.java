package com.app.user.exceptions;

import java.time.LocalDateTime;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiErrorDetails errorDetails = new ApiErrorDetails(LocalDateTime.now(), "MethodArgumentNotValidException in GEH", ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiErrorDetails errorDetails = new ApiErrorDetails(LocalDateTime.now(), "Method Not Allowed", ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(UserNameNotFoundException.class)
	public final ResponseEntity<ApiErrorDetails> handleUserNameNotFoundException(UserNameNotFoundException ex, WebRequest request) {
		ApiErrorDetails errorDetails = new ApiErrorDetails(LocalDateTime.now(), "Username Not Found", ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND); 
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<ApiErrorDetails> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
		ApiErrorDetails errorDetails = new ApiErrorDetails(LocalDateTime.now(), ex.getClass().getName(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND); 
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<ApiErrorDetails> handleOrderNotFoundException(OrderNotFoundException ex, WebRequest request) {
		ApiErrorDetails errorDetails = new ApiErrorDetails(LocalDateTime.now(), ex.getClass().getName(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND); 
	}
}
