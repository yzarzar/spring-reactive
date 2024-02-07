package com.springreactive.productservicemongoreactive;

import com.springreactive.productservicemongoreactive.dto.ProductDto;
import com.springreactive.productservicemongoreactive.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@RequiredArgsConstructor
public class ProductServiceMongoReactiveApplication {

	private final ProductService productService;

	@Bean
	public ApplicationRunner runner() {
		return r -> {
			ProductDto p1 = new ProductDto("4k-tv",10000);
			ProductDto p2 = new ProductDto("5k-tv",20000);
			ProductDto p3 = new ProductDto("6k-tv",30000);
			ProductDto p4 = new ProductDto("7k-tv",40000);
			Flux.just(p1, p2, p3, p4)
					.concatWith(newProducts())
					.flatMap(p -> productService.insertProduct(Mono.just(p)))
					.subscribe(System.out::println);
		};
	}

	@Bean
	public Flux<ProductDto> newProducts() {
		return Flux.range(1,1000)
				.delayElements(Duration.ofSeconds(1000))
				.map(i -> new ProductDto("product-" + i,
						ThreadLocalRandom.current().nextInt(1,100)));
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceMongoReactiveApplication.class, args);
	}

}
