package ua.com.alevel.service.cart.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.cart.Cart;
import ua.com.alevel.persistence.entity.cart.CartItem;
import ua.com.alevel.persistence.entity.sunglasses.Sunglasses;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.cart.CartItemRepository;
import ua.com.alevel.persistence.repository.cart.CartRepository;
import ua.com.alevel.service.cart.CartService;
import ua.com.alevel.service.sunglasses.SunglassesService;

import java.util.Collection;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final SunglassesService sunglassesService;

    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, SunglassesService sunglassesService) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.sunglassesService = sunglassesService;
    }

    @Override
    public Cart findById(Long id) {
        return cartRepository.findById(id).get();
    }

    @Override
    public Cart findByUser(User user) {
        return cartRepository.findByUser(user);
    }

    @Override
    public Collection<CartItem> findAllByCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).get();
        return cartItemRepository.findAllByCart(cart);
    }

    @Override
    public void updateCart(Collection<CartItem> cartItems) {
        cartItems.forEach(v -> {
            if (!v.getActive()) {
                cartItemRepository.delete(v);
            } else {
                cartItemRepository.save(v);
            }
        });
    }

    @Override
    public void addToCart(Long id, Cart cart, int qty) {
        CartItem cartItem = cartItemRepository.findByCartIdAndSunglassesId(cart.getId(), id);
        CartItem cartItemNew;
        if (cartItem == null) {
            cartItemNew = new CartItem();
            Sunglasses sunglassesCurrent = sunglassesService.findById(id).get();
            cartItemNew.setSunglasses(sunglassesCurrent);
            cartItemNew.setQuantity(qty);
            cartItemNew.setCart(cart);
            cartItemRepository.save(cartItemNew);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + qty);
            cartItemRepository.save(cartItem);
        }
    }
}
