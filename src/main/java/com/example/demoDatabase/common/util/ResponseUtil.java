package com.example.demoDatabase.common.util;

import com.example.demoDatabase.common.model.ResponseDTO;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.Collections;

@UtilityClass
public class ResponseUtil {
    public static ResponseEntity<ResponseDTO> get(Object object, HttpStatus status) {
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(object)
                        .hasErrors(false)
                        .errors(Collections.emptyList())
                        .timestamp(String.valueOf(LocalDateTime.now()))
                        .status(status.value())
                        .build(),
                status
        );
    }

    public static ResponseEntity<ResponseDTO> error(RuntimeException exception, HttpStatus status) {
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(null)
                        .hasErrors(true)
                        .errors(ExeptionUtil.getError(exception))
                        .timestamp(String.valueOf(LocalDateTime.now()))
                        .status(status.value())
                        .build(),
                status
        );
    }

    public static ResponseEntity<ResponseDTO> error(MethodArgumentNotValidException exception, HttpStatus status) {
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .content(null)
                        .hasErrors(true)
                        .errors(ExeptionUtil.getError(exception))
                        .timestamp(String.valueOf(LocalDateTime.now()))
                        .status(status.value())
                        .build(),
                status
        );
    }
}
