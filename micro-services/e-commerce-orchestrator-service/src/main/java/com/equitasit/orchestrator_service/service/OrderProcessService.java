package com.equitasit.orchestrator_service.service;

import com.equitasit.orchestrator_service.dto.OrderDTO;
import com.equitasit.orchestrator_service.dto.StatusDTO;
import com.equitasit.orchestrator_service.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class OrderProcessService {

    @Autowired
    private RuntimeService runtimeService;

    public StatusDTO execute(OrderDTO orderDTO) {
        Map<String, Object> variables = new HashMap<>();
        variables.put(Constants.ORDER, orderDTO);


        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("order_process", variables);
        StatusDTO statusDTO = new StatusDTO("process started, id = " + processInstance.getProcessInstanceId());
        log.info(" order process  success");
        return statusDTO;
    }
}
