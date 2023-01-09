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
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class OrderProcessService {

    @Autowired
    private RuntimeService runtimeService;

    public StatusDTO execute(OrderDTO orderDTO) {
        log.info(" order process  started");
        StatusDTO statusDTO = null;
        try {
            Map<String, Object> variables = new HashMap<>();
            variables.put(Constants.ORDER, orderDTO);


            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("order_process", variables);


            statusDTO = new StatusDTO("process completed, id = " + processInstance.getProcessInstanceId());
            log.info(" order process  success");
        } catch (Exception e) {
            log.error("error", e);
            statusDTO = new StatusDTO(e.getMessage());
        }
        log.info(" order process  completed");
        return statusDTO;
    }
}
