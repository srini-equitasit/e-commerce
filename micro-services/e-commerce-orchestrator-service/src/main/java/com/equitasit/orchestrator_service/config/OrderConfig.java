package com.equitasit.orchestrator_service.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@NoArgsConstructor
@ConfigurationProperties(prefix = "app.orchestrator.order")
@Configuration
public class OrderConfig {

    private String inventoryUrl;

    private String paymentUrl;

    private String deliveryUrl;
}
