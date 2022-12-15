package com.equitasit.product_service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ConfigurationProperties(prefix = "product.app")
@Setter
@Getter
@Configuration
public class ProductAppConfig {

    private String priceUrl;

    private String sellerUrl;
}
