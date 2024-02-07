package com.reactive.myfristreactiveproject.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Data
public class InputFailValidationException extends RuntimeException{

    private static final String MSG = "allowed range is 10 to 20.";
    private static final int errorCode = 100;
    private final int input;

    public int getInput() {
        return input;
    }

    public InputFailValidationException(int input) {
        super(MSG);
        this.input = input;
    }
}
