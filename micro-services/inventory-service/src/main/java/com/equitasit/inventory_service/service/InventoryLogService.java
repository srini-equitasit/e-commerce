package com.equitasit.inventory_service.service;

import com.equitasit.inventory_service.dto.InventoryLogDTO;
import com.equitasit.inventory_service.entity.Inventory;
import com.equitasit.inventory_service.entity.InventoryLog;
import com.equitasit.inventory_service.repository.InventoryLogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class InventoryLogService {

    @Autowired
    ModelMapper modelMapper;


    @Autowired
    private InventoryLogRepository inventoryLogRepository;

    public List<InventoryLogDTO> get() {
        List<InventoryLog> inventoryLogList = inventoryLogRepository.findAll();
        return inventoryLogList.stream().map(inventoryLog -> modelMapper.map(inventoryLog, InventoryLogDTO.class)).collect(Collectors.toList());

    }


}
