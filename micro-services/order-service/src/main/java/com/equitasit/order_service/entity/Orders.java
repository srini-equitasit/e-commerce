package com.equitasit.order_service.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Orders {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "status")
    private String status;


    @Column(name = "order_created_dt")
    private Date orderCreatedDt;

    @OneToMany(mappedBy = "order")
    private List<ProductOrder> products;
}
