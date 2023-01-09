package com.equitasit.orchestrator_service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
@Builder
public class InventoryTxDTO implements Serializable {
    private static final long serialVersionUID = 1l;
    private Integer txQty;

    private Integer userId;

    private Integer sellerId;

    private Integer productId;

    private Integer orderId;
}
