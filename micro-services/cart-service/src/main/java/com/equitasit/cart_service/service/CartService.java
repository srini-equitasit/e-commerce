package com.equitasit.cart_service.service;

import com.equitasit.cart_service.dto.CartDTO;
import com.equitasit.cart_service.entity.Cart;
import com.equitasit.cart_service.repository.CartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CartRepository cartRepository;

    public CartDTO save(CartDTO cartDTO) {

        Cart cart = modelMapper.map(cartDTO, Cart.class);
        Cart savedCart = cartRepository.save(cart);
        return modelMapper.map(savedCart, CartDTO.class);
    }

    public CartDTO get(Integer id) {
        Cart savedCart = cartRepository.get(id);
        return modelMapper.map(savedCart, CartDTO.class);
    }
}
