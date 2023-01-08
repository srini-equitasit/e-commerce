package com.equitasit.orchestrator_service.service.inventory;

import com.equitasit.orchestrator_service.config.OrderConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ReverseDebitInventoryService extends InventoryService {
    public ReverseDebitInventoryService(RestTemplate restTemplate, OrderConfig orderConfig) {
        super(restTemplate, orderConfig, "/inventory/credit");
    }
}
