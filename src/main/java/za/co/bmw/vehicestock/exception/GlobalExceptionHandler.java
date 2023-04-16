package za.co.bmw.vehicestock.exception;

import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import za.co.bmw.vehicestock.model.ApiError;

/**
 *
 * @author cliff
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus httpstatus, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();   
        String errorMessage  = fieldError != null ? fieldError.getDefaultMessage() : "";
        log.error("MethodArgumentNotValidException occured: {}", ex.getMessage());
        
        return new ResponseEntity<>(new ApiError("VALIDATION FAILED", errorMessage), headers, httpstatus);
    }
    
    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus httpstatus, WebRequest request) {
        log.error("HttpMessageNotReadableException occured: {}", ex.getMessage());        
        return new ResponseEntity<>(new ApiError("VALIDATION FAILED", "Request payload is malformed"),  HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        log.error("ConstraintViolationException occurred: {}", ex.getMessage());
        return new ResponseEntity<>(new ApiError("VALIDATION FAILED", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> handleSQLException(SQLException ex) {
        log.error("SQLException occured: {}", ex.getMessage());
        return new ResponseEntity<>(new ApiError("ERROR", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> handleHttpClientError(HttpClientErrorException ex) {
        log.error("HttpClientErrorException occured: ", ex.getMessage());
        return new ResponseEntity<>(new ApiError("ERROR", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<Object> handleHttpServerError(HttpServerErrorException ex) {
        log.error("HttpServerErrorException occured: {}", ex.getMessage());
        return new ResponseEntity<>(new ApiError("ERROR", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }  
    
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccess(EmptyResultDataAccessException ex) {
        log.error("EmptyResultDataAccessException occured: {}", ex.getMessage());
        return new ResponseEntity<>(new ApiError("ERROR", "Record not found"), HttpStatus.NOT_FOUND);
    }  
    
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<Object> handleDateTimeParseException(DateTimeParseException ex) {
        log.error("DateTimeParseException occured: {}", ex.getMessage());
        return new ResponseEntity<>(new ApiError("ERROR", "Invalid date format"), HttpStatus.BAD_REQUEST);
    }   
}
