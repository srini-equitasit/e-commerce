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

            OrderDTO orderDTO = (OrderDTO) execution.getVariable(Constants.ORDER);

            Boolean isDebit = (Boolean) execution.getVariable(Constants.IS_DEBIT);

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

            ResponseEntity<String> responseEntity = restTemplate.exchange(orderConfig.getPaymentUrl(), HttpMethod.POST, request, String.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                execution.setVariable(Constants.VALID, true);
            } else {
                execution.setVariable(Constants.VALID, false);
            }
        } catch (Exception e) {
            log.error("payment error ", e);
            execution.setVariable(Constants.VALID, false);
        }
    }
}
