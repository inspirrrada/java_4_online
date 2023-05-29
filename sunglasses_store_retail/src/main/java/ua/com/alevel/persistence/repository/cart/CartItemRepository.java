package ua.com.alevel.persistence.repository.cart;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.cart.Cart;
import ua.com.alevel.persistence.entity.cart.CartItem;
import ua.com.alevel.persistence.entity.sunglasses.Sunglasses;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.List;

@Repository
public interface CartItemRepository extends BaseRepository<CartItem> {

    List<CartItem> findAllByCart(Cart cart);
    CartItem findByCartIdAndSunglasses(Long cartId, Sunglasses sunglasses);
    CartItem findByCartIdAndSunglassesId(Long cartId, Long SunglassesId);
}
