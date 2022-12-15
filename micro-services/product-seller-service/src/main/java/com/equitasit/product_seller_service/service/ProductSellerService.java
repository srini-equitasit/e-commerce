package com.equitasit.product_seller_service.service;

import com.equitasit.product_seller_service.dto.ProductSellerDTO;
import com.equitasit.product_seller_service.entity.ProductSeller;
import com.equitasit.product_seller_service.exception.ProductSellerException;
import com.equitasit.product_seller_service.repository.ProductSellerRepository;
import com.equitasit.product_seller_service.util.MsgConstants;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Slf4j
@Transactional(readOnly = true)
public class ProductSellerService {
    @Autowired
    private ProductSellerRepository productSellerRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ProductSellerDTO save(ProductSellerDTO productSellerDTO) {
        log.debug("enter");
        ProductSeller productSeller = modelMapper.map(productSellerDTO, ProductSeller.class);
        ProductSeller savedProductSeller = productSellerRepository.save(productSeller);
        log.debug("exit");
        return modelMapper.map(savedProductSeller, ProductSellerDTO.class);
    }

    @Transactional
    public ProductSellerDTO update(ProductSellerDTO productSellerDTO) {
        log.debug("enter");
        Optional<ProductSeller> optionalProductSeller = productSellerRepository.findById(productSellerDTO.getId());
        if (!optionalProductSeller.isPresent()) {
            log.error("product not found for product seller id {} ", productSellerDTO.getId());
            throw new ProductSellerException(MsgConstants.PRODUCT_SELLER_NOT_FOUND);
        }
        ProductSeller productSeller = optionalProductSeller.get();
        modelMapper.map(productSellerDTO, productSeller);
        ProductSeller savedProductSeller = productSellerRepository.save(productSeller);
        log.debug("exit");
        return modelMapper.map(savedProductSeller, ProductSellerDTO.class);

    }

    public ProductSellerDTO get(Integer productSellerId) {
        log.debug("enter");
        Optional<ProductSeller> optionalProduct = productSellerRepository.findById(productSellerId);
        if (!optionalProduct.isPresent()) {
            log.error("product seller not found for product id {} ", productSellerId);
            throw new ProductSellerException(MsgConstants.PRODUCT_SELLER_NOT_FOUND);
        }
        ProductSeller productSeller = optionalProduct.get();
        log.debug("exit");
        return modelMapper.map(productSeller, ProductSellerDTO.class);
    }

    public List<ProductSellerDTO> getAll() {
        log.debug("enter");
        List<ProductSellerDTO> list = productSellerRepository.findAll().stream().map(p -> modelMapper.map(p, ProductSellerDTO.class)).collect(Collectors.toList());
        log.info("products seller size {} ", list.size());
        log.debug("product seller list {} ", list);
        log.debug("exit");
        return list;
    }

    @Transactional
    public void remove(Integer prodSellerId) {
        Optional<ProductSeller> optionalProduct = productSellerRepository.findById(prodSellerId);
        if (!optionalProduct.isPresent()) {
            log.error("product seller not found for product seller id {} ", prodSellerId);
            throw new ProductSellerException(MsgConstants.PRODUCT_SELLER_NOT_FOUND);
        }
        ProductSeller product = optionalProduct.get();
        productSellerRepository.delete(product);
    }

}
