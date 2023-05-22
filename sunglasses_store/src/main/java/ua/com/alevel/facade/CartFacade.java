package ua.com.alevel.facade;

import ua.com.alevel.data.response.CartFormDto;
import ua.com.alevel.data.response.SunglassesCartDto;
import ua.com.alevel.data.response.SunglassesPLPDto;
import ua.com.alevel.persistence.entity.cart.Cart;
import ua.com.alevel.persistence.entity.cart.CartItem;
import ua.com.alevel.persistence.entity.user.User;

import java.util.Collection;

public interface CartFacade {

    Cart findById(Long id);
    Cart findByUser(User user);
    Collection<SunglassesCartDto> findAllByCart(Long cartId);
    void updateCart(CartFormDto cartFormDto, Long cartId);
}