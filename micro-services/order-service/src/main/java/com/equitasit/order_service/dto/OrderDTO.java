package com.equitasit.order_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class OrderDTO implements Serializable {


    private static final long serialVersionUID = 1l;

    private List<ProductOrderDTO> products;



    private Integer userId;

    private String email;

    private Integer id;

    private String status;


}
