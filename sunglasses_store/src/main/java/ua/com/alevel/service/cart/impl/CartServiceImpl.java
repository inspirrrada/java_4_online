package ua.com.alevel.service.cart.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.cart.Cart;
import ua.com.alevel.persistence.entity.cart.CartItem;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.cart.CartItemRepository;
import ua.com.alevel.persistence.repository.cart.CartRepository;
import ua.com.alevel.service.cart.CartService;

import java.util.Collection;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
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
//        cartItemRepository.saveAll(cartItems);
    }
}
