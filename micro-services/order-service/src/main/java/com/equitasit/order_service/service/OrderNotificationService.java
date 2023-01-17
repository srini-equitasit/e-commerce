package com.equitasit.order_service.service;

import com.equitasit.order_service.dto.OrderDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
@Slf4j
public class OrderNotificationService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper mapper;

    @Value("${app.order.notifyTopic}")
    private String orderNotifyTopic;

    public void notifyOrder(OrderDTO orderDTO) throws Exception {

        log.info("notify topic {} ",orderNotifyTopic);

        ListenableFuture<SendResult<String, String>> resultFuture = kafkaTemplate.send(orderNotifyTopic, mapper.writeValueAsString(orderDTO));

        SendResult<String, String> sendResult = resultFuture.get();

        log.info(" createOrder  success {}", sendResult);
    }

}
