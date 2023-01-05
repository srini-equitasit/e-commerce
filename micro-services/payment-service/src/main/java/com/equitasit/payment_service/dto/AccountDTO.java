package com.equitasit.payment_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class AccountDTO {

    private Integer id;

    private Double amount;

    private Integer userId;
}
