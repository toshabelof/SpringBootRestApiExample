package com.belovstech.prjsalews.controller;

import com.belovstech.prjsalews.exception.AlreadyExistException;
import com.belovstech.prjsalews.exception.CustomException;
import com.belovstech.prjsalews.exception.DataBaseException;
import com.belovstech.prjsalews.exception.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LogManager.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<CustomException> alreadyExistException(AlreadyExistException exception) {
        logger.error(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new CustomException(exception.getMessage(), exception.fillInStackTrace()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CustomException> notFoundException(NotFoundException exception) {
        logger.error(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new CustomException(exception.getMessage(), exception.fillInStackTrace()));
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<CustomException> dataBaseException(DataBaseException exception) {
        logger.error(exception.getMessage(), exception);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new CustomException(exception.getMessage(), exception.fillInStackTrace()));
    }

}
