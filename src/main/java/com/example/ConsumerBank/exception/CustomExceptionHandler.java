package com.example.ConsumerBank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(value = CustomerNotFoundException.class)
	public final ResponseEntity<String> handlingEmplyeeNotFoundException(CustomerNotFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { AccountNotFoundException.class })
	public ResponseEntity<String> handlingAccountFoundException(AccountNotFoundException exception) {
		return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { InsufficientBalanceException.class })
	public ResponseEntity<String> handlingInsufficientException(InsufficientBalanceException exception) {
		return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public final ResponseEntity<String> HandlingMethodArgumentNotValidException(
			MethodArgumentNotValidException exception) {
		BindingResult result = exception.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		String[] message = ((String) Objects.requireNonNull(((FieldError) fieldErrors.get(0)).getDefaultMessage()))
				.split(";;");
		return new ResponseEntity<String>(message[0], HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = ConstraintViolationException.class)
	public final ResponseEntity<String> HandlingConstraintViolationException(ConstraintViolationException exception) {
		Set<ConstraintViolation<?>> set = exception.getConstraintViolations();
		Optional<ConstraintViolation<?>> messages = set.stream().findFirst();
		if (messages.isPresent()) {
			String[] message = ((String) Objects.requireNonNull(((ConstraintViolation) messages.get()).getMessage()))
					.split(";;");
			return new ResponseEntity(message[0], HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity("", HttpStatus.BAD_REQUEST);
		}
	}

	@ExceptionHandler(value = Exception.class)
	public String HandlingEmployeExistException(Exception exception) {
		return exception.getMessage();
	}
}
