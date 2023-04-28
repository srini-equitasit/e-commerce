package com.equitasit.order_service.service.consumer;

import com.equitasit.order_service.dto.OrderDTO;
import com.equitasit.order_service.service.OrderNotificationService;
import com.equitasit.order_service.service.OrdersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderConsumer {

    @Autowired
    private OrderNotificationService orderNotificationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrdersService ordersService;

    @KafkaListener(topics = {"${app.order.completeTopic}"})
    public void consume(ConsumerRecord<Integer, String> record) throws Exception {

        log.info("record {} ", record);

        log.info("received = " + record.value() + " with key " + record.key());

        OrderDTO orderDTO = objectMapper.readValue(record.value(), OrderDTO.class);


        ordersService.update(orderDTO);

        orderNotificationService.notifyOrder(orderDTO);

        log.info("order notified");

    }
}

