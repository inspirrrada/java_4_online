package ua.com.alevel.facade;

import org.springframework.stereotype.Service;
import ua.com.alevel.data.response.SunglassesCartDto;
import ua.com.alevel.data.response.SunglassesPLPDto;
import ua.com.alevel.persistence.entity.cart.Cart;
import ua.com.alevel.persistence.entity.cart.CartItem;
import ua.com.alevel.persistence.entity.sunglasses.Sunglasses;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.service.CartService;

import java.util.Collection;

@Service
public class CartFacadeImpl implements CartFacade {

    private final CartService cartService;

    public CartFacadeImpl(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public Cart findByUser(User user) {
        return cartService.findByUser(user);
    }

    @Override
    public Collection<SunglassesCartDto> findAllByCart(Long cartId) {
        Collection<CartItem> cartItems = cartService.findAllByCart(cartId);
        return cartItems
                .stream()
                .map(SunglassesCartDto::new)
                .toList();
    }
}
