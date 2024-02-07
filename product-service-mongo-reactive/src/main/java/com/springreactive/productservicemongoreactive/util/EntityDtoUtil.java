package com.springreactive.productservicemongoreactive.util;

import com.springreactive.productservicemongoreactive.dto.ProductDto;
import com.springreactive.productservicemongoreactive.entity.Product;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static Product toEntity(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }

    public static ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        BeanUtils.copyProperties(product,dto);
        return dto;
    }
}
