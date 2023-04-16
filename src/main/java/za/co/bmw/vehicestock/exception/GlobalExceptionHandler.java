package za.co.bmw.vehicestock.exception;

import java.sql.SQLException;
import javax.validation.ConstraintViolationException;
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
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus httpstatus, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();   
        String errorMessage  = fieldError != null ? fieldError.getDefaultMessage() : "";
       
        return new ResponseEntity<>(new ApiError("VALIDATION FAILED", errorMessage), headers, httpstatus);
    }
    
    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus httpstatus, WebRequest request) {
        return new ResponseEntity<>(new ApiError("VALIDATION FAILED", ex.getMessage()), headers, httpstatus);
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        return new ResponseEntity<>(new ApiError("VALIDATION FAILED", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> handleSQLException(SQLException ex) {
        return new ResponseEntity<>(new ApiError("ERROR", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> handleHttpClientError(HttpClientErrorException ex) {
        return new ResponseEntity<>(new ApiError("ERROR", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<Object> handleHttpServerError(HttpServerErrorException ex) {
        return new ResponseEntity<>(new ApiError("ERROR", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }  
    
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccess(EmptyResultDataAccessException ex) {
        return new ResponseEntity<>(new ApiError("ERROR", "Record not found"), HttpStatus.NOT_FOUND);
    }   
}
