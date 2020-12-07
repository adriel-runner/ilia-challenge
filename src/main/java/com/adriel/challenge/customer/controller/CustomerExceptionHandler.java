package com.adriel.challenge.customer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adriel.challenge.customer.service.CostumerNotExistsException;
import com.adriel.challenge.customer.service.CustomerCpfChangingException;

@RestController
@ControllerAdvice
public class CustomerExceptionHandler {

	@ExceptionHandler(value = { MethodArgumentNotValidException.class})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public List<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		List<String> filedErrors = new ArrayList<>();
		List<FieldError>errors= exception.getBindingResult().getFieldErrors();
		for (FieldError error : errors) {
			filedErrors.add(String.format("%s - %s", error.getField(), error.getDefaultMessage()));
        }
		return filedErrors;
	}
	
	@ExceptionHandler(value = { CostumerNotExistsException.class })
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String handleNotExistsException(Exception exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler(value = { CustomerCpfChangingException.class })
	@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
	public String handleCustomerCpfChangingException(Exception exception) {
		return exception.getMessage();
	}
}