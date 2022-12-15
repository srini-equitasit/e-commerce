package com.equitasit.product_service.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class ProductDataDTO {
    ProductDTO product;
    ProductPriceDTO price;

    List<ProductSellerDTO> sellers;
}
