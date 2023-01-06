package com.equitasit.inventory_service.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "inventory")
@Setter
@Getter
@NoArgsConstructor
@ToString
@IdClass(InventoryId.class)
public class Inventory {


    @Column(name = "qty")
    private Integer qty;

    @Id
    @Column(name = "seller_id")
    private Integer sellerId;

    @Id
    @Column(name = "seller_id")
    private Integer productId;
}
