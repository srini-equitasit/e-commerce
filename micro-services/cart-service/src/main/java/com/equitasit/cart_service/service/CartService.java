package com.equitasit.cart_service.service;

import com.equitasit.cart_service.dto.CartItemDTO;
import com.equitasit.cart_service.entity.CartItem;
import com.equitasit.cart_service.repository.CartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CartRepository cartRepository;

    public List<CartItemDTO> save(Integer userId, CartItemDTO cartDTO) {

        CartItem cart = modelMapper.map(cartDTO, CartItem.class);
        List<CartItem> cartItemList = cartRepository.save(userId, cart);
        return cartItemList.stream().map(savedCart -> modelMapper.map(savedCart, CartItemDTO.class)).collect(Collectors.toList());
    }

    public List<CartItemDTO> get(Integer userId) {
        List<CartItem> cartItemList =  cartRepository.get(userId);

        return cartItemList.stream().map(savedCart -> modelMapper.map(savedCart, CartItemDTO.class)).collect(Collectors.toList());
    }

    public void remove(Integer userId) {
        cartRepository.remove(userId);
    }

    public  Integer getCartCount(Integer userId) {
        return cartRepository.getCartCount(userId);
    }
}
