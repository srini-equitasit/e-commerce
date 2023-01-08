package com.equitasit.delivery_service.repository;

import com.equitasit.delivery_service.entity.DeliveryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryInfoRepository extends JpaRepository<DeliveryInfo, Integer> {

    public List<DeliveryInfo> findByOrderId(Integer orderId);
}
