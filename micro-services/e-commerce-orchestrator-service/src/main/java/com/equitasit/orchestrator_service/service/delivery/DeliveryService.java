package com.equitasit.orchestrator_service.service.delivery;

import com.equitasit.orchestrator_service.config.OrderConfig;
import com.equitasit.orchestrator_service.dto.DeliveryInfoDTO;
import com.equitasit.orchestrator_service.dto.OrderDTO;
import com.equitasit.orchestrator_service.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class DeliveryService implements JavaDelegate {


    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private OrderConfig orderConfig;

    private String resourceUrl = "/delivery";


    @Override
    public void execute(DelegateExecution execution) throws Exception {

        try {
            log.info(" delivery process  started");
            OrderDTO orderDTO = (OrderDTO) execution.getVariable(Constants.ORDER);

            List<DeliveryInfoDTO> deliveryInfoDTOS = orderDTO.getProducts().stream().map(ordProd -> {
                DeliveryInfoDTO deliveryInfoDTO = DeliveryInfoDTO.builder()
                        .qty(ordProd.getQty())
                        .productId(ordProd.getProductId())
                        .userId(orderDTO.getUserId())
                        .orderId(orderDTO.getOrderId())
                        .build();
                return deliveryInfoDTO;
            }).collect(Collectors.toList());

            HttpHeaders headers = new HttpHeaders();

            HttpEntity<List<DeliveryInfoDTO>> request =
                    new HttpEntity<List<DeliveryInfoDTO>>(deliveryInfoDTOS, headers);

            String url = orderConfig.getDeliveryUrl() + this.resourceUrl;
            log.info("url {} ", url);
            ResponseEntity<List<DeliveryInfoDTO>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, new ParameterizedTypeReference<List<DeliveryInfoDTO>>() {
            });
            log.info("response {} ", responseEntity);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                execution.setVariable(Constants.VALID, true);
                execution.setVariable(Constants.DELIVERY_INFO_DTOS, responseEntity.getBody());
            } else {
                execution.setVariable(Constants.VALID, false);
            }
            log.info(" delivery process  success");

        } catch (Exception e) {
            log.error("delivery error ", e);
            execution.setVariable(Constants.VALID, false);
        }
        log.info(" delivery process  completed \n");
    }
}
