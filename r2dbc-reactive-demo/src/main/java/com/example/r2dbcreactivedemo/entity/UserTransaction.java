package com.example.r2dbcreactivedemo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
public class UserTransaction {

    private Integer id;
    private Integer userId;
    private Integer amount;
    private LocalDateTime localDateTime;

    public UserTransaction(Integer id, Integer userId, Integer amount, LocalDateTime localDateTime) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.localDateTime = localDateTime;
    }
}
