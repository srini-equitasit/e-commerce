package com.equitasit.payment_service.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;


@Configuration
@EnableAsync
public class PaymentServiceConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
