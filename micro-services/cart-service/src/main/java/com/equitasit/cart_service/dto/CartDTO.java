package com.equitasit.cart_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class CartDTO {

    private Integer userId;
    Integer count;
}
