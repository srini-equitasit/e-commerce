package com.equitasit.orchestrator_service.service.payment;

import com.equitasit.orchestrator_service.config.OrderConfig;
import com.equitasit.orchestrator_service.dto.OrderDTO;
import com.equitasit.orchestrator_service.dto.PaymentTxDTO;
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


@Slf4j
public class PaymentService implements JavaDelegate {

    private RestTemplate restTemplate;


    private OrderConfig orderConfig;

    private String resourceUrl;

    public PaymentService(RestTemplate restTemplate, OrderConfig orderConfig, String url) {
        this.restTemplate = restTemplate;
        this.orderConfig = orderConfig;
        this.resourceUrl = url;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        try {
            log.info(" payment process  started");
            OrderDTO orderDTO = (OrderDTO) execution.getVariable(Constants.ORDER);
            PaymentTxDTO paymentTxDTO = PaymentTxDTO.builder()
                    .amount(orderDTO.getProducts().stream()
                            .mapToDouble(productOrderDTO -> productOrderDTO.getQty() * productOrderDTO.getQty()).sum())
                    .userId(orderDTO.getUserId())
                    .orderId(orderDTO.getOrderId())
                    .build();

            HttpHeaders headers = new HttpHeaders();

            HttpEntity<PaymentTxDTO> request =
                    new HttpEntity<PaymentTxDTO>(paymentTxDTO, headers);

            String url = orderConfig.getPaymentUrl() + this.resourceUrl;

            log.info("url {} ", url);

            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            log.info("response {} ", responseEntity);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                execution.setVariable(Constants.VALID, true);
            } else {
                execution.setVariable(Constants.VALID, false);
            }
            log.info(" payment process  success");
        } catch (Exception e) {
            log.error("payment error ", e);
            execution.setVariable(Constants.VALID, false);
        }
        log.info(" payment process  completed \n");
    }
}
