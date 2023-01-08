package com.equitasit.orchestrator_service.service.payment;

import com.equitasit.orchestrator_service.config.OrderConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReverseDebitPaymentService extends PaymentService {
    public ReverseDebitPaymentService(RestTemplate restTemplate, OrderConfig orderConfig) {
        super(restTemplate, orderConfig, "/account/credit");
    }
}
