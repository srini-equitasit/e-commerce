package com.equitasit.order_service.repository;

import com.equitasit.order_service.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    List<Orders> findByUserId(Integer userId);

}
