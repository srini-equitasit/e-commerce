package com.equitasit.order_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class ProductOrderDTO implements Serializable {

    private static final long serialVersionUID = 1l;

    private Integer productId;

    private Integer qty;

    private Double price;

    private Integer sellerId;
}
