package com.equitasit.payment_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class AccountLogDTO {

    private Integer id;


    private Double balance;


    private Double amount;


    private String type;


    private Integer userId;
}
