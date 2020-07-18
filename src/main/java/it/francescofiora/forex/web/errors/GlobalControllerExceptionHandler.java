package it.francescofiora.forex.web.errors;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Map<String, Object>> handleConnversion(RuntimeException ex) {
		Map<String, Object> map = Collections.singletonMap("error", ex.getMessage());
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Map<String, Object>> handleItemNotFound(RuntimeException ex) {
		Map<String, Object> map = Collections.singletonMap("error", ex.getMessage());
		return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
	}
}
