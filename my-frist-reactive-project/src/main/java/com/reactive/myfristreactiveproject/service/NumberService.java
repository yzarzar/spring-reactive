package com.reactive.myfristreactiveproject.service;

import com.reactive.myfristreactiveproject.dto.MultiplyDto;
import com.reactive.myfristreactiveproject.dto.NumberDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class NumberService {

    public Mono<NumberDto> findSquare(int input){
        return Mono.fromSupplier(()-> input * input)
                .map(v -> new NumberDto(1));
    }


    public Flux<NumberDto> multiplication(int input) {
        return Flux.range(1, 5)
                //.doOnNext(n -> Duration.duration(1))
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(n -> System.out.println("Welcome to number : " + n))
                .map(i -> new NumberDto(i * input));
    }

    public Mono<NumberDto> multiply(Mono<MultiplyDto> multiplyDtoMono) {
        return multiplyDtoMono
                .map(dto -> dto.getFirst() * dto.getSecond())
                .map(d -> new NumberDto(d));
    }
}
