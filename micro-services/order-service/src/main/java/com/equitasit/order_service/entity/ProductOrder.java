package com.equitasit.order_service.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "product_order")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class ProductOrder {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "price")
    private Double price;


    @Column(name = "seller_id")
    private Integer sellerId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;


}
