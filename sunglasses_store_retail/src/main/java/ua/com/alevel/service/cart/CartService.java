package ua.com.alevel.service.cart;

import ua.com.alevel.persistence.entity.cart.Cart;
import ua.com.alevel.persistence.entity.cart.CartItem;
import ua.com.alevel.persistence.entity.user.User;

import java.util.Collection;

public interface CartService {

    Cart findById(Long id);
    Cart findByUser(User user);
    Collection<CartItem> findAllByCart(Long cartId);
    void updateCart(Collection<CartItem> cartItems);
    void addToCart(Long id, Cart cart, int qty);
}
