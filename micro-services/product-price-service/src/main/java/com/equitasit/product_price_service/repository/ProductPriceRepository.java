package com.equitasit.product_price_service.repository;

import com.equitasit.product_price_service.entity.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Integer> {


    public ProductPrice findByProductId(Integer productId);
}
