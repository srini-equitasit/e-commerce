package com.equitasit.inventory_service.controller;

import com.equitasit.inventory_service.dto.InventoryDTO;
import com.equitasit.inventory_service.dto.InventoryTxDTO;
import com.equitasit.inventory_service.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("inventory")
@Slf4j
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping
    public ResponseEntity save(@RequestBody InventoryDTO inventoryDTO) {

        log.debug("enter");
        log.info("saving the inventoryDTO info {}", inventoryDTO);
        InventoryDTO savedInventoryDTO = inventoryService.save(inventoryDTO);
        log.info("saved savedInventoryDTO info {}", savedInventoryDTO);
        log.debug("exit");
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInventoryDTO);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody InventoryDTO inventoryDTO) {

        log.debug("enter");
        log.info("saving the inventoryDTO info {}", inventoryDTO);
        InventoryDTO savedInventoryDTO = inventoryService.update(inventoryDTO);
        log.info("saved savedInventoryDTO info {}", savedInventoryDTO);
        log.debug("exit");
        return ResponseEntity.status(HttpStatus.OK).body(savedInventoryDTO);
    }

    @GetMapping("{sellerId}/{productId}")
    public ResponseEntity get(@PathVariable("sellerId") Integer sellerId, @PathVariable("productId") Integer productId) {

        log.debug("enter");
        log.info("saving the inventoryDTO sellerId, productId {},{}", sellerId, productId);
        InventoryDTO savedInventoryDTO = inventoryService.get(sellerId, productId);
        log.info("saved savedInventoryDTO info {}", savedInventoryDTO);
        log.debug("exit");
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInventoryDTO);
    }

    @GetMapping
    public ResponseEntity getAll() {

        log.debug("enter");

        List<InventoryDTO> inventoryDTOList = inventoryService.getAll();
        log.info("saved savedInventoryDTO size {}", inventoryDTOList.size());
        log.debug("saved savedInventoryDTO info {}", inventoryDTOList);
        log.debug("exit");
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryDTOList);
    }

    @PostMapping("debit")
    public ResponseEntity debit(@RequestBody InventoryTxDTO... inventoryTxDTO) {

        log.debug("enter");
        log.info("saving the inventoryTxDTO info {}", inventoryTxDTO);
        inventoryService.debit(inventoryTxDTO);
        log.info("saved inventoryTxDTO info ");
        log.debug("exit");
        return ResponseEntity.ok().build();
    }

    @PostMapping("credit")
    public ResponseEntity credit(@RequestBody InventoryTxDTO... inventoryTxDTO) {

        log.debug("enter");
        log.info("saving the inventoryTxDTO info {}", inventoryTxDTO);
        inventoryService.credit(inventoryTxDTO);
        log.info("saved inventoryTxDTO info ");
        log.debug("exit");
        return ResponseEntity.ok().build();
    }
}
