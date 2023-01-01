package com.equitasit.cart_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable {


    private Integer id;

    private Integer userId;

    private Integer qty;

    private Integer productId;

    private Double price;
}
