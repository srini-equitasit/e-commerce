package com.equitasit.inventory_service.repository;

import com.equitasit.inventory_service.entity.Inventory;
import com.equitasit.inventory_service.entity.InventoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, InventoryId> {
}
