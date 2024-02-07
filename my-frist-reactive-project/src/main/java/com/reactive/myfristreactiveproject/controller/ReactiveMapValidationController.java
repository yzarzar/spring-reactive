package com.reactive.myfristreactiveproject.controller;

import com.reactive.myfristreactiveproject.dto.InputFailValidationException;
import com.reactive.myfristreactiveproject.dto.NumberDto;
import com.reactive.myfristreactiveproject.service.NumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive-math")
@RequiredArgsConstructor
public class ReactiveMapValidationController {

    private final NumberService numberService;

    @GetMapping("/square/{input}/throw")
    public Mono<NumberDto> findSquare(@PathVariable int input) {
        if (input < 10 || input > 20) {
            throw new InputFailValidationException(input);
        }
        return numberService.findSquare(12);
    }
}
