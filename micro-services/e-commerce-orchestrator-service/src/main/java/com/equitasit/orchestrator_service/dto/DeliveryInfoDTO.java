package com.equitasit.orchestrator_service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
public class DeliveryInfoDTO {

    private Integer id;


    private Integer productId;


    private Integer qty;


    private Integer sellerId;


    private Integer userId;
    private Integer orderId;
}
