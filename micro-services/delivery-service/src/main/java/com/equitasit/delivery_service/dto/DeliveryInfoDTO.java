package com.equitasit.delivery_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class DeliveryInfoDTO {

    private Integer id;


    private Integer productId;


    private Integer qty;


    private Integer sellerId;


    private Integer userId;


    private Date bookingDate;


    private String bookingId;
}
