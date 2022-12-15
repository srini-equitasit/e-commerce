package com.equitasit.product_price_service.service;

import com.equitasit.product_price_service.dto.ProductPriceDTO;
import com.equitasit.product_price_service.entity.ProductPrice;
import com.equitasit.product_price_service.exception.ProductPriceException;
import com.equitasit.product_price_service.repository.ProductPriceRepository;
import com.equitasit.product_price_service.util.MsgConstants;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
public class ProductPriceService {
    @Autowired
    private ProductPriceRepository productPriceRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ProductPriceDTO save(ProductPriceDTO productPriceDTO) {
        log.debug("enter");
        ProductPrice productPrice = modelMapper.map(productPriceDTO, ProductPrice.class);
        ProductPrice savedProductPrice = productPriceRepository.save(productPrice);
        log.debug("exit");
        return modelMapper.map(savedProductPrice, ProductPriceDTO.class);
    }

    @Transactional
    public ProductPriceDTO update(ProductPriceDTO productPriceDTO) {
        log.debug("enter");
        Optional<ProductPrice> optionalProduct = productPriceRepository.findById(productPriceDTO.getId());
        if (!optionalProduct.isPresent()) {
            log.error("product price is not found for product price id {} ", productPriceDTO.getId());
            throw new ProductPriceException(MsgConstants.PRODUCT_PRICE_NOT_FOUND);
        }
        ProductPrice productPrice = optionalProduct.get();
        modelMapper.map(productPriceDTO, productPrice);
        ProductPrice savedProductPrice = productPriceRepository.save(productPrice);
        log.debug("exit");
        return modelMapper.map(savedProductPrice, ProductPriceDTO.class);

    }

    public ProductPriceDTO get(Integer productPriceId) {
        log.debug("enter");
        Optional<ProductPrice> optionalProduct = productPriceRepository.findById(productPriceId);
        if (!optionalProduct.isPresent()) {
            log.error("product price is  not found for product price id {} ", productPriceId);
            throw new ProductPriceException(MsgConstants.PRODUCT_PRICE_NOT_FOUND);
        }
        ProductPrice productPrice = optionalProduct.get();
        log.debug("exit");
        return modelMapper.map(productPrice, ProductPriceDTO.class);
    }

    public List<ProductPriceDTO> getAll() {
        log.debug("enter");
        List<ProductPriceDTO> list = productPriceRepository.findAll().stream().map(p -> modelMapper.map(p, ProductPriceDTO.class)).collect(Collectors.toList());
        log.info("products price size {} ", list.size());
        log.debug("product price list {} ", list);
        log.debug("exit");
        return list;
    }

    @Transactional
    public void remove(Integer prodPriceId) {
        log.debug("enter");
        Optional<ProductPrice> optionalProductPrice = productPriceRepository.findById(prodPriceId);
        if (!optionalProductPrice.isPresent()) {
            log.error("product price is not found for product price id {} ", prodPriceId);
            throw new ProductPriceException(MsgConstants.PRODUCT_PRICE_NOT_FOUND);
        }
        ProductPrice productPrice = optionalProductPrice.get();
        log.debug("exit");
        productPriceRepository.delete(productPrice);
    }

    public ProductPriceDTO getProductPriceForProdId(Integer productId) {
        log.debug("enter");

        log.info("productId {}", productId);
        ProductPrice productPrice = productPriceRepository.findByProductId(productId);
        log.info("productPrice {}", productPrice);
        log.debug("exit");
        return productPrice != null ? modelMapper.map(productPrice, ProductPriceDTO.class) : null;
    }
}
