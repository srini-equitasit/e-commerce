package com.equitasit.orchestrator_service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Builder
public class InventoryTxDTO {
    private Integer txQty;

    private Integer userId;

    private Integer sellerId;

    private Integer productId;

    private Integer orderId;
}
