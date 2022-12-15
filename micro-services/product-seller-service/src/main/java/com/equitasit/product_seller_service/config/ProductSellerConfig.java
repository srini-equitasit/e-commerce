package com.equitasit.product_seller_service.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductSellerConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
