package com.equitasit.orchestrator_service.service.inventory;

import com.equitasit.orchestrator_service.config.OrderConfig;
import com.equitasit.orchestrator_service.dto.DeliveryInfoDTO;
import com.equitasit.orchestrator_service.dto.InventoryTxDTO;
import com.equitasit.orchestrator_service.dto.OrderDTO;
import com.equitasit.orchestrator_service.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
public class InventoryService implements JavaDelegate {


    private RestTemplate restTemplate;


    private OrderConfig orderConfig;

    private String resourceUri;

    public InventoryService(RestTemplate restTemplate, OrderConfig orderConfig, String resourceUri) {
        this.restTemplate = restTemplate;
        this.orderConfig = orderConfig;
        this.resourceUri = resourceUri;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        try {
            log.info(" inventory process  started");
            OrderDTO orderDTO = (OrderDTO) execution.getVariable(Constants.ORDER);
            List<InventoryTxDTO> inventoryTxDTOList = orderDTO.getProducts().stream().map(productOrderDTO -> {
                InventoryTxDTO inventoryTxDTO = InventoryTxDTO.builder()
                        .txQty(productOrderDTO.getQty())
                        .orderId(orderDTO.getOrderId())
                        .productId(productOrderDTO.getProductId())
                        .sellerId(productOrderDTO.getSellerId())
                        .userId(orderDTO.getUserId())
                        .build();
                return inventoryTxDTO;
            }).collect(Collectors.toList());

            HttpHeaders headers = new HttpHeaders();

            HttpEntity<List<InventoryTxDTO>> request =
                    new HttpEntity<List<InventoryTxDTO>>(inventoryTxDTOList, headers);

            String url = orderConfig.getInventoryUrl() + this.resourceUri;
            log.info("url  {} ", url);

            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            log.info("response {} ", responseEntity);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                execution.setVariable(Constants.VALID, true);
            } else {
                execution.setVariable(Constants.VALID, false);
            }
            log.info(" inventory process  success");
        } catch (Exception e) {
            log.error("inventory error ", e);
            execution.setVariable(Constants.VALID, false);
        }
        log.info(" inventory process  completed \n");
    }
}
