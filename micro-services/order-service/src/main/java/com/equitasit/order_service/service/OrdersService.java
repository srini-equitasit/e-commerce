package com.equitasit.order_service.service;

import com.equitasit.order_service.dto.OrderDTO;
import com.equitasit.order_service.entity.Orders;
import com.equitasit.order_service.exception.OrdersException;
import com.equitasit.order_service.repository.OrdersRepository;
import com.equitasit.order_service.util.MsgConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrdersService {

    @Value("${app.order.createTopic}")
    private String orderCreateTopic;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper mapper;


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public OrderDTO createOrder(OrderDTO orderDTO) throws Exception {

        orderDTO.setStatus("ORDER_PENDING");

        OrderDTO savedOrderDTO = save(orderDTO);

        ListenableFuture<SendResult<String, String>> resultFuture = kafkaTemplate.send(orderCreateTopic, mapper.writeValueAsString(savedOrderDTO));

        SendResult<String, String> sendResult = resultFuture.get();

        log.info(" createOrder  success {}", sendResult);

        return savedOrderDTO;
    }


    public OrderDTO save(OrderDTO orderDTO) {

        Orders orders = modelMapper.map(orderDTO, Orders.class);
        Orders savedOrders = ordersRepository.save(orders);
        return modelMapper.map(savedOrders, OrderDTO.class);
    }

    public OrderDTO update(OrderDTO orderDTO) {

        Optional<Orders> orderOptional = ordersRepository.findById(orderDTO.getId());
        if (orderOptional.isEmpty()) {
            throw new OrdersException(MsgConstants.ORDER_NOT_FOUND);
        }
        Orders orders = orderOptional.get();
        modelMapper.map(orderDTO, orders);
        Orders savedOrders = ordersRepository.save(orders);
        return modelMapper.map(savedOrders, OrderDTO.class);
    }

    public OrderDTO getOrder(Integer orderId) {

        Optional<Orders> orderOptional = ordersRepository.findById(orderId);
        if (orderOptional.isEmpty()) {
            throw new OrdersException(MsgConstants.ORDER_NOT_FOUND);
        }
        Orders orders = orderOptional.get();

        return modelMapper.map(orders, OrderDTO.class);
    }

    public void remove(Integer orderId) {

        Optional<Orders> orderOptional = ordersRepository.findById(orderId);
        if (orderOptional.isEmpty()) {
            throw new OrdersException(MsgConstants.ORDER_NOT_FOUND);
        }

        ordersRepository.deleteById(orderId);

    }

    public List<OrderDTO> getAllOrders() {
        List<Orders> ordersList = ordersRepository.findAll();
        List<OrderDTO> orderDTOList = ordersList.stream().map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());
        return orderDTOList;
    }

    public List<OrderDTO> getAllOrders(Integer userId) {
        List<Orders> ordersList = ordersRepository.findByUserId(userId);
        List<OrderDTO> orderDTOList = ordersList.stream().map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());
        return orderDTOList;
    }

}
