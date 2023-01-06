package com.equitasit.inventory_service.controller;

import com.equitasit.inventory_service.dto.InventoryDTO;
import com.equitasit.inventory_service.dto.InventoryLogDTO;
import com.equitasit.inventory_service.dto.InventoryTxDTO;
import com.equitasit.inventory_service.service.InventoryLogService;
import com.equitasit.inventory_service.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("inventory/log")
@Slf4j
public class InventoryLogController {

    @Autowired
    private InventoryLogService inventoryLogService;

    @GetMapping
    public ResponseEntity getAll() {

        log.debug("enter");

        List<InventoryLogDTO> inventoryLogDTOList = inventoryLogService.get();
        log.info("saved inventoryLogDTOList size {}", inventoryLogDTOList.size());
        log.debug("saved inventoryLogDTOList info {}", inventoryLogDTOList);
        log.debug("exit");
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryLogDTOList);
    }

}
