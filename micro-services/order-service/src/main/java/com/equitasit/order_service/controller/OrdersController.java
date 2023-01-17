package com.equitasit.order_service.controller;

import com.equitasit.order_service.dto.OrderDTO;
import com.equitasit.order_service.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
@Slf4j
public class OrdersController {

    @Autowired
    private OrdersService ordersService;


    @PostMapping
    public ResponseEntity create(@RequestBody OrderDTO orderDTO) throws Exception {
        log.debug("enter");
        log.info("getting orderDTO info for act id {}", orderDTO);
        OrderDTO createdOrderDTO = ordersService.createOrder(orderDTO);
        log.info("order info {}", createdOrderDTO);
        log.debug("exit");
        return ResponseEntity.ok(createdOrderDTO);
    }


    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable("id") Integer orderId) {
        log.debug("enter");
        log.info("getting user info for act id {}", orderId);
        OrderDTO orderDTO = ordersService.getOrder(orderId);
        log.info("order info {}", orderDTO);
        log.debug("exit");
        return ResponseEntity.ok(orderDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") Integer orderId) {

        log.debug("enter");
        log.info("removing order info for id {}", orderId);

        ordersService.remove(orderId);

        log.info("getting order info for id {}", orderId);
        log.debug("exit");
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity get() {
        log.debug("enter");

        List<OrderDTO> orderDTOList = ordersService.getAllOrders();

        log.info("orders size {}", orderDTOList.size());
        log.debug("exit");
        return ResponseEntity.ok(orderDTOList);
    }

    @GetMapping("user/{id}")
    public ResponseEntity getByUserID(@PathVariable("id") Integer userId) {
        log.debug("enter");

        List<OrderDTO> orderDTOList = ordersService.getAllOrders(userId);

        log.info("orders size {}", orderDTOList.size());
        log.debug("exit");
        return ResponseEntity.ok(orderDTOList);
    }

}
