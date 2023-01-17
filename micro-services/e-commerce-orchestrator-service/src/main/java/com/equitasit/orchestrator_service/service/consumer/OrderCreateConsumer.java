package com.equitasit.orchestrator_service.service.consumer;

import com.equitasit.orchestrator_service.dto.OrderDTO;
import com.equitasit.orchestrator_service.dto.StatusDTO;
import com.equitasit.orchestrator_service.service.OrderProcessService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderCreateConsumer {


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    OrderProcessService orderProcessService;

    @KafkaListener(topics = {"${app.orchestrator.order.createTopic}"})
    public void consume(ConsumerRecord<Integer, String> record) throws Exception {

        log.info("record {} ", record);

        log.info("received = " + record.value() + " with key " + record.key());

        OrderDTO orderDTO = objectMapper.readValue(record.value(), OrderDTO.class);

        StatusDTO statusDTO = orderProcessService.execute(orderDTO);

        log.info("process execution completed , statusDTO {} ", statusDTO);

    }
}

