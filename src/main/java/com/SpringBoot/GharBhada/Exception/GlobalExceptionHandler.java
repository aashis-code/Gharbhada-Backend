package com.SpringBoot.GharBhada.Exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	//Login failed
	@ExceptionHandler(InvalidUsernameOrPassword.class)
	public ResponseEntity<String> invalidUsernameOrPassword(InvalidUsernameOrPassword ex){
		String message = ex.getMessage();
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	//Validation Field Exception Handling
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, List<String>>> methodArgumentValidException(MethodArgumentNotValidException ex){
		Map<String, List<String>> errorHashMap = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String field = ((FieldError)error).getField();
			String defaultMessage = error.getDefaultMessage();
			
			if(errorHashMap.containsKey(field)) {
				errorHashMap.get(field).add(defaultMessage);
			}
			else {
				List<String> errorMessages = new ArrayList<>();
				errorMessages.add(defaultMessage);
				errorHashMap.put(field, errorMessages);
			}
		});
		return new ResponseEntity<>(errorHashMap, HttpStatus.BAD_REQUEST);
	}
	
	
	//Resource Not Found Exception Handling
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, String>> resourceNotFoundException(ResourceNotFoundException ex){
		String message = ex.getMessage();
		return new ResponseEntity<>(Map.of("message", message), HttpStatus.NOT_FOUND);
	}
	
	//User Already exist
	@ExceptionHandler(UserAlreadyExist.class)
	public ResponseEntity<Map<String, String>> userAlreadyExist(UserAlreadyExist ex){
		String message = ex.getMessage();
		return new ResponseEntity<>(Map.of("message", message), HttpStatus.BAD_REQUEST);
	}
	
	//False claim of home ownership
	@ExceptionHandler(NoSpecificHomeOwnership.class)
	public ResponseEntity<String> noSpecificHomeOwnership(NoSpecificHomeOwnership ex){
		String message = ex.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
	}
	
	
    @ExceptionHandler(FileSizeLimitExcededException.class)
    public ResponseEntity<Object> handleFileSizeLimitExceededException(
        FileSizeLimitExcededException ex) {
        // Customize your error response here
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("actualFileSize", ex.getActualFileSize());
        errorResponse.put("maxFileSize", ex.getMaxFileSize());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(errorResponse);
    }
}
