package com.reactive.myfristreactiveproject.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NumberDto {

    private Date date = new Date();
    private int number;

    public NumberDto(int number) {
        this.number = number;
    }
}
