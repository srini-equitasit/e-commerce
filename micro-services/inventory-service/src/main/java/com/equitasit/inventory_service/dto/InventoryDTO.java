package com.equitasit.inventory_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@NoArgsConstructor
@ToString
public class InventoryDTO {



    private Integer qty;

    private Integer productId;

    private Integer sellerId;
}
