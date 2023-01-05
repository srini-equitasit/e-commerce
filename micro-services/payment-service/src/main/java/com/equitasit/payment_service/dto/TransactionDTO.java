package com.equitasit.payment_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TransactionDTO {
    private Integer actId;

    private Double amount;

    private Integer userId;
}
