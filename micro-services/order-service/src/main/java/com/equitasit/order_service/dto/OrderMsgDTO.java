package com.equitasit.order_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class OrderMsgDTO {
    private String status;

    private OrderDTO orderDTO;
}
