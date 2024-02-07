package com.reactive.myfristreactiveproject.exceptionHandler;

import com.reactive.myfristreactiveproject.dto.InputFailValidationException;
import com.reactive.myfristreactiveproject.dto.InputFaildValidationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class InputValidationHandler {

    public ResponseEntity<InputFaildValidationResponse> handleException(InputFailValidationException ex) {
        InputFaildValidationResponse response = new InputFaildValidationResponse();
        response.setInput(ex.getInput());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
