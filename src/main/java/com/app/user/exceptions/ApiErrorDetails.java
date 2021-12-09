package com.app.user.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiErrorDetails {
	
	private LocalDateTime timestamp;
	
	private String message;
	
	private String errorDetails;
	
	private String path;
}
