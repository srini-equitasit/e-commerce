package com.equitasit.cart_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDTO {
    private String id;
    private Integer userId;
    private Integer qty;
    private Integer productId;

    private Double price;

}
