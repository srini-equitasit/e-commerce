package com.equitasit.inventory_service.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class InventoryLogDTO {

    private Integer id;


    private Double qty;


    private Double txQty;

    private Integer sellerId;


    private String type;


    private Integer userId;
}
