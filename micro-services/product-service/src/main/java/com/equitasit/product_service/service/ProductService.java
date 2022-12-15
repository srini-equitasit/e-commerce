package com.equitasit.product_service.service;

import com.equitasit.product_service.dto.ProductDTO;
import com.equitasit.product_service.entity.Product;
import com.equitasit.product_service.exception.ProductException;
import com.equitasit.product_service.repository.ProductRepository;
import com.equitasit.product_service.util.MsgConstants;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ProductDTO save(ProductDTO productDTO) {
        log.debug("enter");
        Product product = modelMapper.map(productDTO, Product.class);
        Product savedProduct = productRepository.save(product);
        log.debug("exit");
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Transactional
    public ProductDTO update(ProductDTO productDTO) {
        log.debug("enter");
        Optional<Product> optionalProduct = productRepository.findById(productDTO.getId());
        if (!optionalProduct.isPresent()) {
            log.error("product not found for product id {} ", productDTO.getId());
            throw new ProductException(MsgConstants.PRODUCT_NOT_FOUND);
        }
        Product product = optionalProduct.get();
        modelMapper.map(productDTO, product);
        Product savedProduct = productRepository.save(product);
        log.debug("exit");
        return modelMapper.map(savedProduct, ProductDTO.class);

    }

    public ProductDTO get(Integer productId) {
        log.debug("enter");
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()) {
            log.error("product not found for product id {} ", productId);
            throw new ProductException(MsgConstants.PRODUCT_NOT_FOUND);
        }
        Product product = optionalProduct.get();
        log.debug("exit");
        return modelMapper.map(product, ProductDTO.class);
    }

    public List<ProductDTO> getAll() {
        log.debug("enter");
        List<ProductDTO> list = productRepository.findAll().stream().map(p -> modelMapper.map(p, ProductDTO.class)).collect(Collectors.toList());
        log.info("products size {} ", list.size());
        log.debug("product list {} ", list);
        log.debug("exit");
        return list;
    }

    @Transactional
    public void remove(Integer prodId) {
        Optional<Product> optionalProduct = productRepository.findById(prodId);
        if (!optionalProduct.isPresent()) {
            log.error("product not found for product id {} ", prodId);
            throw new ProductException(MsgConstants.PRODUCT_NOT_FOUND);
        }
        Product product = optionalProduct.get();
        productRepository.delete(product);
    }


}
