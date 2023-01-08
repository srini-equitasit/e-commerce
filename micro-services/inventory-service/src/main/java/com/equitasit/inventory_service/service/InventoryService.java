package com.equitasit.inventory_service.service;

import com.equitasit.inventory_service.dto.InventoryDTO;
import com.equitasit.inventory_service.dto.InventoryTxDTO;
import com.equitasit.inventory_service.entity.Inventory;
import com.equitasit.inventory_service.entity.InventoryId;
import com.equitasit.inventory_service.entity.InventoryLog;
import com.equitasit.inventory_service.exception.InventoryException;
import com.equitasit.inventory_service.repository.InventoryLogRepository;
import com.equitasit.inventory_service.repository.InventoryRepository;
import com.equitasit.inventory_service.util.MsgConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class InventoryService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryLogRepository inventoryLogRepository;

    @Transactional
    public InventoryDTO save(InventoryDTO inventoryDTO) {
        Inventory inventory = inventoryRepository.save(modelMapper.map(inventoryDTO, Inventory.class));
        return modelMapper.map(inventory, InventoryDTO.class);
    }

    @Transactional
    public InventoryDTO update(InventoryDTO inventoryDTO) {

        Optional<Inventory> optionalInventory = inventoryRepository.findById(new InventoryId(inventoryDTO.getSellerId(), inventoryDTO.getProductId()));
        if (!optionalInventory.isPresent()) {
            throw new InventoryException(MsgConstants.INVENTORY_NOT_FOUND);
        }

        Inventory inventory = optionalInventory.get();

        modelMapper.map(inventoryDTO, inventory);

        inventoryRepository.save(inventory);

        return modelMapper.map(inventory, InventoryDTO.class);
    }

    public InventoryDTO get(Integer sellerId, Integer productId) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(new InventoryId(sellerId, productId));
        if (!optionalInventory.isPresent()) {
            throw new InventoryException(MsgConstants.INVENTORY_NOT_FOUND);
        }
        Inventory inventory = optionalInventory.get();
        return modelMapper.map(inventory, InventoryDTO.class);
    }

    public List<InventoryDTO> getAll() {

        List<Inventory> inventoryList = inventoryRepository.findAll();
        return inventoryList.stream().map(inv -> modelMapper.map(inv, InventoryDTO.class)).collect(Collectors.toList());
    }

    @Transactional
    public void remove(Integer sellerId, Integer productId) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(new InventoryId(sellerId, productId));
        if (!optionalInventory.isPresent()) {
            throw new InventoryException(MsgConstants.INVENTORY_NOT_FOUND);
        }

        inventoryRepository.delete(optionalInventory.get());
    }

    @Transactional
    public void debit(InventoryTxDTO inventoryTxDTO) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(new InventoryId(inventoryTxDTO.getSellerId(), inventoryTxDTO.getProductId()));
        if (!optionalInventory.isPresent()) {
            throw new InventoryException(MsgConstants.INVENTORY_NOT_FOUND);
        }
        Inventory inventory = optionalInventory.get();
        int quantity = inventory.getQty() - inventoryTxDTO.getTxQty();
        if (quantity < 0) {
            throw new InventoryException(MsgConstants.IN_SUFFICIENT_INVENTORY);
        }
        inventory.setQty(quantity);
        InventoryLog inventoryLog = createInventoryLog("DEBIT", inventory, inventoryTxDTO);
        inventoryRepository.save(inventory);
        inventoryLogRepository.save(inventoryLog);


    }

    @Transactional
    public void credit(InventoryTxDTO inventoryTxDTO) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(new InventoryId(inventoryTxDTO.getSellerId(), inventoryTxDTO.getProductId()));
        if (!optionalInventory.isPresent()) {
            throw new InventoryException(MsgConstants.INVENTORY_NOT_FOUND);
        }
        Inventory inventory = optionalInventory.get();
        int quantity = inventory.getQty() + inventoryTxDTO.getTxQty();

        inventory.setQty(quantity);
        InventoryLog inventoryLog = createInventoryLog("CREDIT", inventory, inventoryTxDTO);

        inventoryRepository.save(inventory);
        inventoryLogRepository.save(inventoryLog);

    }

    InventoryLog createInventoryLog(String type, Inventory inventory, InventoryTxDTO inventoryTxDTO) {
        InventoryLog inventoryLog = new InventoryLog();
        inventoryLog.setType(type);
        inventoryLog.setQty(inventory.getQty());
        inventoryLog.setTxQty(inventoryTxDTO.getTxQty());
        inventoryLog.setUserId(inventoryTxDTO.getUserId());
        inventoryLog.setProductId(inventoryTxDTO.getProductId());
        inventoryLog.setSellerId(inventoryTxDTO.getSellerId());
        inventoryLog.setOrderId(inventoryTxDTO.getOrderId());

        return inventoryLog;
    }


}
