package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.cart.Cart;
import ua.com.alevel.persistence.entity.cart.CartItem;

import java.util.List;

@Repository
public interface CartItemRepository extends BaseRepository<CartItem> {

    List<CartItem> findAllByCart(Cart cart);
}
