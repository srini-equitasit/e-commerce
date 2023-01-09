package com.equitasit.orchestrator_service.service.delivery;

import com.equitasit.orchestrator_service.config.OrderConfig;
import com.equitasit.orchestrator_service.dto.DeliveryInfoDTO;
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

@Service
@Slf4j
public class ReverseDeliveryService implements JavaDelegate {

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private OrderConfig orderConfig;

    private String resourceUrl = "/delivery";

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info(" reverse delivery process  started");
        try {
            List<DeliveryInfoDTO> deliveryInfoDTOS = (List<DeliveryInfoDTO>) execution.getVariable(Constants.DELIVERY_INFO_DTOS);

            HttpHeaders headers = new HttpHeaders();

            HttpEntity<List<DeliveryInfoDTO>> request =
                    new HttpEntity<List<DeliveryInfoDTO>>(deliveryInfoDTOS, headers);

            String url = orderConfig.getDeliveryUrl() + this.resourceUrl;
            log.info("url {}", url);
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            log.info("response {} ", responseEntity);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                execution.setVariable(Constants.VALID, true);
                execution.removeVariable(Constants.DELIVERY_INFO_DTOS);
            } else {
                execution.setVariable(Constants.VALID, false);
            }
            log.info(" reverse delivery process  success");
        } catch (Exception e) {
            log.error("revere delivery error ", e);
            execution.setVariable(Constants.VALID, false);
        }
        log.info(" reverse delivery process  completed \n");
    }
}
