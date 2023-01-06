package com.equitasit.inventory_service.repository;

import com.equitasit.inventory_service.entity.InventoryLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryLogRepository extends JpaRepository<InventoryLog, Integer> {
}
