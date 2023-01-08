package com.equitasit.orchestrator_service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PaymentTxDTO {
      private Double amount;

    private Integer userId;
    private Integer orderId;
}
