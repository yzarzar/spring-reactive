package com.example.r2dbcreactivedemo.service;

import com.example.r2dbcreactivedemo.dto.UserDto;
import com.example.r2dbcreactivedemo.repository.UserRepository;
import com.example.r2dbcreactivedemo.utils.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Flux<UserDto> all() {
        return userRepository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> getUserById(final int userId) {
        return userRepository.findById(userId)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> createUser(Mono<UserDto> userDtoMono) {
        return userDtoMono
                .map(EntityDtoUtil::toEntity)
                .flatMap(userRepository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<Void> deleteUser(int id) {
        return userRepository.deleteById(id);
    }

    public Mono<UserDto> updateUser(int id, Mono<UserDto> userDtoMono) {
        return userRepository.findById(id)
                .map(Optional::of)
                .defaultIfEmpty(Optional.empty())
                .flatMap(u -> userDtoMono
                        .map(EntityDtoUtil::toEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(userRepository::save)
                .map(EntityDtoUtil::toDto);
    }
}
