package com.equitasit.payment_service.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "account_log")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class AccountLog {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "tx_amount")
    private Double txAmount;

    @Column(name = "type")
    private String type;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "order_id")
    private Integer orderId;
}
