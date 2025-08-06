package com.restaurantapp.menuservice.handler;

import com.restaurantapp.menuservice.exception.IncorrectDataException;
import com.restaurantapp.menuservice.exception.NotFoundException;
import com.restaurantapp.menuservice.model.dto.ErrorMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IncorrectDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ErrorMessageDto> incorrectDataErrorResponse(IncorrectDataException exception) {
        log.error("IncorrectDataException thrown with message = {} and stack trace = {}", exception.getMessage(), exception.getStackTrace());
        return ResponseEntity.badRequest().body(new ErrorMessageDto(exception.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ErrorMessageDto> notFoundErrorResponse(IncorrectDataException exception) {
        log.error("NotFoundException thrown with message = {} and stack trace = {}", exception.getMessage(), exception.getStackTrace());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessageDto(exception.getMessage()));
    }
}
