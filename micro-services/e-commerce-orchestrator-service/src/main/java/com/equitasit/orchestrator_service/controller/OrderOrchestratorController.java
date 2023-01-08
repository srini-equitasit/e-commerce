package com.equitasit.orchestrator_service.controller;

import com.equitasit.orchestrator_service.dto.OrderDTO;
import com.equitasit.orchestrator_service.dto.ProductOrderDTO;
import com.equitasit.orchestrator_service.dto.StatusDTO;
import com.equitasit.orchestrator_service.service.OrderProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orchestrator/order")
@Slf4j
public class OrderOrchestratorController {

    @Autowired
    OrderProcessService orderProcessService;

    @PostMapping
    public ResponseEntity perform(@RequestBody OrderDTO orderDTO) {

        StatusDTO statusDTO = orderProcessService.execute(orderDTO);
        return ResponseEntity.ok(statusDTO);
    }
}
