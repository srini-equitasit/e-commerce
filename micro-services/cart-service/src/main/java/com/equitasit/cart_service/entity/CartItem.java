package com.equitasit.cart_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem implements Serializable {

    private static final long serialVersionUID = 1l;

    private Integer userId;

    private Integer qty;

    private Integer productId;

    private Double price;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return userId.equals(cartItem.userId) && productId.equals(cartItem.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, productId);
    }
}
