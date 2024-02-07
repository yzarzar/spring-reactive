package com.springreactive.productservicemongoreactive.service;

import com.springreactive.productservicemongoreactive.dto.ProductDto;
import com.springreactive.productservicemongoreactive.repository.ProductRepository;
import com.springreactive.productservicemongoreactive.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final Sinks.Many<ProductDto> sink;

    public Mono<ProductDto> insertProduct(Mono<ProductDto> productDtoMono) {
        return productDtoMono
                .map(EntityDtoUtil::toEntity)
                .flatMap(productRepository::insert)
                .map(EntityDtoUtil::toDto)
                .doOnNext(sink::tryEmitNext);
    }
}
