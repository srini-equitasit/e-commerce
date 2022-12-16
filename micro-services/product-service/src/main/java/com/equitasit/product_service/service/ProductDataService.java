package com.equitasit.product_service.service;

import com.equitasit.product_service.dto.ProductDTO;
import com.equitasit.product_service.dto.ProductDataDTO;
import com.equitasit.product_service.dto.ProductPriceDTO;
import com.equitasit.product_service.dto.ProductSellerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductDataService {

    @Autowired
    private ProductDataResilienceRestService productDataResilienceRestService;
    @Autowired
    private ProductService productService;


    public ProductDataDTO getProductData(Integer prodId) throws Exception {

        ProductDataDTO productDataDTO = new ProductDataDTO();


        ProductDTO productDTO = productService.get(prodId);

        CompletableFuture<ProductPriceDTO> productPriceDTOCompletableFuture = productDataResilienceRestService.getProductPrice(prodId);

        CompletableFuture<List<ProductSellerDTO>> productSellerDTOListCompletableFuture = productDataResilienceRestService.getSellersInfo(prodId);

        productDataDTO.setProduct(productDTO);

        productDataDTO.setPrice(productPriceDTOCompletableFuture.get());

        productDataDTO.setSellers(productSellerDTOListCompletableFuture.get());


        return productDataDTO;
    }
}
