package com.equitasit.inventory_service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InventoryTxDTO {
    private Integer txQty;

    private Integer userId;

    private Integer sellerId;

    private Integer productId;
}
