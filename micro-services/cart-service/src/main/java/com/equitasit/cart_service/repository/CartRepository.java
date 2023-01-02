package com.equitasit.cart_service.repository;

import com.equitasit.cart_service.entity.CartItem;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CartRepository {

    private static final String CART_KEY = "CART";
    private static final String CART_CNT_KEY = "CART_CNT";

    private HashOperations hashOperations;

    public CartRepository(RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    public List<CartItem> save(Integer userId, CartItem cartItem) {
        List<CartItem> cartItemList = (List<CartItem>) this.hashOperations.get(CART_KEY, userId);
        if (cartItemList == null) {
            cartItemList = new ArrayList<>();
        }
        if (cartItemList.contains(cartItem)) {
            for (CartItem ci : cartItemList) {
                if (ci.equals(cartItem)) {
                    //merge the data for matched ones
                    ci.setQty(cartItem.getQty());
                    ci.setPrice(cartItem.getPrice());
                    break;
                }
            }

        } else {
            cartItemList.add(cartItem);
        }

        this.hashOperations.put(CART_KEY, userId, cartItemList);
        this.hashOperations.put(CART_CNT_KEY, userId, cartItemList.size());
        return cartItemList;
    }

    public List<CartItem> get(Integer userId) {

        List<CartItem> cartItemList = (List<CartItem>) this.hashOperations.get(CART_KEY, userId);
        if (cartItemList == null) {
            cartItemList = new ArrayList<>();
        }
        return cartItemList;
    }

    public Integer getCartCount(Integer userId) {

        Integer cartCnt = (Integer) this.hashOperations.get(CART_KEY, userId);

        return cartCnt;
    }


    public void remove(Integer userId) {

        this.hashOperations.delete(CART_KEY, userId);
        this.hashOperations.delete(CART_CNT_KEY, userId);

    }
}
