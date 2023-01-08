package com.equitasit.orchestrator_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class ProductOrderDTO implements Serializable {

    private static final long serialVersionUID = 1l;

    private Integer productId;

    private Integer qty;

    private Double price;

    private Integer sellerId;
}
