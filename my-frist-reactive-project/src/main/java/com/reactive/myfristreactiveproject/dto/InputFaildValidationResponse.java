package com.reactive.myfristreactiveproject.dto;

import lombok.Data;

@Data
public class InputFaildValidationResponse {

    private int errorCode;
    private int input;
    private String errorMsg;
}
