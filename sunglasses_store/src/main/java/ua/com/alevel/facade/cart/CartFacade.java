package ua.com.alevel.facade.cart;

import ua.com.alevel.dto.cart.CartFormDto;
import ua.com.alevel.dto.cart.SunglassesCartDto;
import ua.com.alevel.persistence.entity.cart.Cart;
import ua.com.alevel.persistence.entity.user.User;

import java.util.Collection;

public interface CartFacade {

    Cart findById(Long id);
    Cart findByUser(User user);
    CartFormDto findAllByCart(Long cartId);
    void updateCart(CartFormDto cartFormDto, Long cartId);
}
