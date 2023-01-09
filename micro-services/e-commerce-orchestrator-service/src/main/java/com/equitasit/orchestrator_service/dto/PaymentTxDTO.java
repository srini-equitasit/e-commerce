package com.equitasit.orchestrator_service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Builder
public class PaymentTxDTO implements Serializable {
    private Double amount;

    private Integer userId;
    private Integer orderId;
}
