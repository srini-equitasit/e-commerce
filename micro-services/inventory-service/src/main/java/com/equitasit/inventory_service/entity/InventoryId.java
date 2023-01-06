package com.equitasit.inventory_service.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryId implements Serializable {
    private static final long serialVersionUID = 1l;

    private Integer sellerId;


    private Integer productId;
}
