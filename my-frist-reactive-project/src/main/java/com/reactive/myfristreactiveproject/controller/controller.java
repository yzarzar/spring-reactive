package com.reactive.myfristreactiveproject.controller;

import com.reactive.myfristreactiveproject.dto.MultiplyDto;
import com.reactive.myfristreactiveproject.dto.NumberDto;
import com.reactive.myfristreactiveproject.service.NumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/reactive")
@RequiredArgsConstructor
public class controller {

    private final NumberService numberService;

    @GetMapping(value = "list/{input}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<NumberDto> multiplicationNumber(@PathVariable("input") int input) {
        return numberService.multiplication(input);
    }

    @PostMapping(value = "/multiply")
    public Mono<NumberDto> multiply(@RequestBody Mono<MultiplyDto> multiplyDtoMono, @RequestHeader Map<String, String> headers) {
        System.out.println(headers);
        return numberService.multiply(multiplyDtoMono);
    }
}
