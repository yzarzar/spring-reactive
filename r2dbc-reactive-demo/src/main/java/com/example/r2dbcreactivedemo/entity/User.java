package com.example.r2dbcreactivedemo.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@ToString
@Table(name = "users")
public class User {

    @Id
    private Integer id;
    private String name;
    private Integer balance;

    public User(Integer id, String name, Integer balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

}
