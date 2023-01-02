package com.equitasit.cart_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CartItemDTO {
    private Integer userId;
    private Integer qty;
    private Integer productId;

    private Double price;


}
