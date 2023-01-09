package com.equitasit.orchestrator_service.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Setter
@Getter
@Builder
@Jacksonized
public class DeliveryInfoDTO implements Serializable {

    private Integer id;


    private Integer productId;


    private Integer qty;


    private Integer sellerId;


    private Integer userId;
    private Integer orderId;
}
