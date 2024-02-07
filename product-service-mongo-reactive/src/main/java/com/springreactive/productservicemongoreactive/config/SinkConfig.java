package com.springreactive.productservicemongoreactive.config;

import com.springreactive.productservicemongoreactive.dto.ProductDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Sinks;

@Configuration
public class SinkConfig {

    @Bean
    public Sinks.Many<ProductDto> sink() {
        return Sinks.many().replay().limit(1);
    }
}
