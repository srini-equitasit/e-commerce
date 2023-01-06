package com.equitasit.inventory_service.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "inventory_log")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class InventoryLog {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "tx_qty")
    private Integer txQty;

    @Column(name = "type")
    private String type;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "seller_id")
    private Integer sellerId;

    @Column(name = "product_id")
    private Integer productId;
}
