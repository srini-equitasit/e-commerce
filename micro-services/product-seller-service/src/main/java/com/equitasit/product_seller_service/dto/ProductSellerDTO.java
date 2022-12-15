package com.equitasit.product_seller_service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProductSellerDTO {

    private Integer id;

    private String name;

    private String location;
    private Integer productId;

}
