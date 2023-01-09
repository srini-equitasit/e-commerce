package com.equitasit.orchestrator_service.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Builder
@ToString
public class PaymentTxDTO implements Serializable {
    private Double amount;

    private Integer userId;
    private Integer orderId;
}
