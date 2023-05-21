package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.cart.Cart;
import ua.com.alevel.persistence.entity.user.User;

@Repository
public interface CartRepository extends BaseRepository<Cart> {

    Cart findByUser(User user);
}
