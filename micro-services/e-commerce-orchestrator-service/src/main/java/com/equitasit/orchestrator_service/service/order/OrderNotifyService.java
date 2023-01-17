package com.equitasit.orchestrator_service.service.order;

import com.equitasit.orchestrator_service.dto.OrderDTO;
import com.equitasit.orchestrator_service.util.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
@Slf4j
public class OrderNotifyService implements JavaDelegate {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.orchestrator.order.completeTopic}")
    private String orderCompleteTopic;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void execute(DelegateExecution execution) {
        log.info(" OrderNotifyService started");
        try {
            OrderDTO orderDTO = (OrderDTO) execution.getVariable(Constants.ORDER);

            orderDTO.setStatus("ORDER_CREATED");

            ListenableFuture<SendResult<String, String>> resultFuture = kafkaTemplate.send(orderCompleteTopic, mapper.writeValueAsString(orderDTO));
            SendResult<String, String> sendResult = resultFuture.get();

            execution.setVariable(Constants.VALID, true);
            log.info(" OrderNotifyService  success");
        } catch (Exception e) {
            execution.setVariable(Constants.VALID, false);
            log.error("OrderNotifyService error", e);
        }
        log.info(" OrderNotifyService completed \n");

    }
}
