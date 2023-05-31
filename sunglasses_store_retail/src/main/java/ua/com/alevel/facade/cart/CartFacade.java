package ua.com.alevel.facade.cart;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.dto.cart.CartFormDto;
import ua.com.alevel.persistence.entity.cart.Cart;
import ua.com.alevel.persistence.entity.user.User;

public interface CartFacade {

    Cart findById(Long id);
    Cart findByUser(User user);
    CartFormDto findAllByCart(Long cartId);
    void updateCart(CartFormDto cartFormDto, Long cartId);
    void addToCart(Long id, WebRequest webRequest, Cart cart);
}
