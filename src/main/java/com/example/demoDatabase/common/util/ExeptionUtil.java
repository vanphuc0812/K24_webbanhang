package com.example.demoDatabase.common.util;

import lombok.experimental.UtilityClass;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;
import java.util.List;

@UtilityClass
public class ExeptionUtil {
    public static List<String> getError(RuntimeException e) {
        return List.of(e.getMessage());
    }

    public static List<String> getError(MethodArgumentNotValidException e) {
        return Arrays.stream(e.getDetailMessageArguments())
                .map((object -> object.toString()))
                .filter(object -> !"".equals(object)).toList();

    }
} //List<Object> -> List<String>
