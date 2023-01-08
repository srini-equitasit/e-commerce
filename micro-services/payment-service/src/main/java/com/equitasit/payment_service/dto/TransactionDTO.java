package com.equitasit.payment_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Setter
@Getter
@NoArgsConstructor
public class TransactionDTO {
    private Integer actId;

    private Double amount;

    private Integer userId;
    private Integer orderId;
}
