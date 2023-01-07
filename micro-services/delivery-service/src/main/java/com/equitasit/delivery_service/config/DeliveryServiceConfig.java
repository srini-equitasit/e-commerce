package com.equitasit.delivery_service.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;


@Configuration
@EnableAsync
public class DeliveryServiceConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
