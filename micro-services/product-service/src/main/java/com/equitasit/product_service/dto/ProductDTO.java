package com.equitasit.product_service.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class ProductDTO {

    private Integer id;


    private String name;


    private String description;
}
