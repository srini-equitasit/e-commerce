package com.equitasit.order_service.repository;

import com.equitasit.order_service.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {
}
