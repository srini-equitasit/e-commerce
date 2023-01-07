package com.equitasit.gateway_service.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "product.app")
@Setter
@Getter
@Configuration
@ToString
public class ECommerceAppConfig {

    private String priceUrl;

    private String sellerUrl;

    private String productUrl;

    private String userUrl;

    private String cartItemsUrl;

    private String inventoryUrl;

    private String paymentsUrl;

    private String notificationUrl;

    private String orderUrl;

    private String deliveryUrl;
}
