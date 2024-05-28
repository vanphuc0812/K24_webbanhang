package com.example.demoDatabase.common.exception;

import com.example.demoDatabase.common.model.ResponseDTO;
import com.example.demoDatabase.common.util.ResponseUtil;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExeptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handleConstraintViolation(MethodArgumentNotValidException exception) {
        return ResponseUtil.error(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({WBHBussinessExeption.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handleConstraintViolation(WBHBussinessExeption exception) {
        return ResponseUtil.error(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ResponseDTO> handleAccessDeniedException(AccessDeniedException exception) {
        return ResponseUtil.error(exception, HttpStatus.FORBIDDEN);
    }

}
