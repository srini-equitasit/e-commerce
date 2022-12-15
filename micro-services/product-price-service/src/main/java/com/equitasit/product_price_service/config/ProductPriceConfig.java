package com.equitasit.product_price_service.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductPriceConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
