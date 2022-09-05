package guru.springframework.msscbeerservice.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MvcExceptionHandler {
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<String>> validationExceptionHandler(ConstraintViolationException exception) {
		List<String> errors = new ArrayList<String>(exception.getConstraintViolations().size());
		
		exception.getConstraintViolations().forEach(violation -> {
			errors.add(violation.getPropertyPath() + " : " + violation.getMessage());
		});
		
		return new ResponseEntity<List<String>>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BindException.class)
	public ResponseEntity<List> bindExceptionHandler(BindException exception){
		List errors = new ArrayList(exception.getAllErrors());
		return new ResponseEntity<List>(errors, HttpStatus.BAD_REQUEST);
	}
	

}
