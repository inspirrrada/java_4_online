package ua.com.alevel.facade;

import org.springframework.stereotype.Service;
import ua.com.alevel.data.response.CartFormDto;
import ua.com.alevel.data.response.SunglassesCartDto;
import ua.com.alevel.persistence.entity.cart.Cart;
import ua.com.alevel.persistence.entity.cart.CartItem;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.SunglassesRepository;
import ua.com.alevel.service.CartService;
import ua.com.alevel.service.SunglassesService;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CartFacadeImpl implements CartFacade {

    private final CartService cartService;
    private final SunglassesService sunglassesService;

    public CartFacadeImpl(CartService cartService, SunglassesRepository sunglassesRepository, SunglassesService sunglassesService) {
        this.cartService = cartService;
        this.sunglassesService = sunglassesService;
    }

    @Override
    public Cart findById(Long id) {
        return cartService.findById(id);
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

    @Override
    public void updateCart(CartFormDto cartFormDto, Long cartId) {
        Collection<SunglassesCartDto> cartFormList = cartFormDto.getCartFormList();
        Collection<CartItem> cartItems = new ArrayList<>();
                cartFormList.stream().forEach(v -> {
                    CartItem cartItem = new CartItem();
                    cartItem.setId(v.getCartItemId());
                    cartItem.setCart(findById(cartId));
                    cartItem.setSunglasses(sunglassesService.findById(v.getId()).get());
                    cartItem.setQuantity(v.getQty());
                    if (v.isShouldBeRemoved()) {
                        cartItem.setActive(false);
                    } else {
                        cartItem.setActive(true);
                    }
                    cartItems.add(cartItem);
                });
             cartService.updateCart(cartItems);
    }
}
