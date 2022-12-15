package com.equitasit.product_seller_service.repository;

import com.equitasit.product_seller_service.entity.ProductSeller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductSellerRepository extends JpaRepository<ProductSeller, Integer> {


    List<ProductSeller> findByProductId(Integer productId);
}
