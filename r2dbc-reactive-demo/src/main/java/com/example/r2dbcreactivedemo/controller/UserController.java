package com.example.r2dbcreactivedemo.controller;

import com.example.r2dbcreactivedemo.dto.UserDto;
import com.example.r2dbcreactivedemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping( produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UserDto> all() {
        return userService.all();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<UserDto>> getUserById(@PathVariable("id") int id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<UserDto> createUser(@RequestBody Mono<UserDto> userDtoMono) {
        return userService.createUser(userDtoMono);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<UserDto>> updateUser(
            @PathVariable("id") int id,
            @RequestBody Mono<UserDto> userDtoMono ) {
        return userService.updateUser(id, userDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable("id") int id) {
        return userService.deleteUser(id);
    }
}
