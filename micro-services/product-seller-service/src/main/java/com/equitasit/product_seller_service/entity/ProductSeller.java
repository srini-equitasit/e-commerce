package com.equitasit.product_seller_service.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "product_seller")
@Setter
@Getter
@ToString
public class ProductSeller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;
    @Column(name = "product_id")
    private Integer productId;
}
