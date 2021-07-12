package com.runsystem.student.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomExceptionHandler {

	// Handle all NotFoundException 404
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handlerNotFoundException(NotFoundException ex, WebRequest req) {
		ErrorResponse message = new ErrorResponse(
				ex.getMessage(),
		        HttpStatus.NOT_FOUND.value(),
		        new Date(),
		        req.getDescription(false));
		return message;
	}

	@ExceptionHandler(DuplicateRecordException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handlerDuplicateRecordException(DuplicateRecordException ex, WebRequest req) {
		ErrorResponse message = new ErrorResponse(
				ex.getMessage(),
		        HttpStatus.BAD_REQUEST.value(),
		        new Date(),
		        req.getDescription(false));
		return message;
	}

	// Handle all undeclared exceptions
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handlerException(Exception ex, WebRequest req) {
		ErrorResponse message = new ErrorResponse(
				ex.getMessage(),
		        HttpStatus.INTERNAL_SERVER_ERROR.value(),
		        new Date(),
		        req.getDescription(false));
		return message;
	}
}
