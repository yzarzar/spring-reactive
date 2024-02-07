package com.springreactive.productservicemongoreactive.repository;

import com.springreactive.productservicemongoreactive.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    Flux<Product> findByPriceBetween(Range<Integer> range);
}
