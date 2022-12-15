package com.equitasit.product_service.service;

import com.equitasit.product_service.dto.ProductDTO;
import com.equitasit.product_service.dto.ProductDataDTO;
import com.equitasit.product_service.dto.ProductPriceDTO;
import com.equitasit.product_service.dto.ProductSellerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDataService {

    @Autowired
    private ProductDataResilienceRestService productDataResilienceRestService;
    @Autowired
    private ProductService productService;


    public ProductDataDTO getProductData(Integer prodId) {

        ProductDataDTO productDataDTO = new ProductDataDTO();


        ProductDTO productDTO = productService.get(prodId);

        ProductPriceDTO productPriceDTO = productDataResilienceRestService.getProductPrice(prodId);

        List<ProductSellerDTO> productSellerDTOList = productDataResilienceRestService.getSellersInfo(prodId);

        productDataDTO.setProduct(productDTO);

        productDataDTO.setPrice(productPriceDTO);

        productDataDTO.setSellers(productSellerDTOList);


        return productDataDTO;
    }
}
