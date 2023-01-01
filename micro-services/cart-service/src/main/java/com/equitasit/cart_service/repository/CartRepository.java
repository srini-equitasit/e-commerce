package com.equitasit.cart_service.repository;

import com.equitasit.cart_service.entity.Cart;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepository {

    private static final String CART_KEY = "CART";

    private HashOperations hashOperations;

    public CartRepository(RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    public Cart save(Cart cart) {
        this.hashOperations.put(CART_KEY, cart.getUserId(), cart);
        return cart;
    }

    public Cart get(Integer id) {

        Cart cart = (Cart) this.hashOperations.get(CART_KEY, id);
        return cart;
    }
}
