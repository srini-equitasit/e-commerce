package com.equitasit.product_price_service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProductPriceDTO {

    private Integer id;

    private Double price;

    private Integer productId;
}
