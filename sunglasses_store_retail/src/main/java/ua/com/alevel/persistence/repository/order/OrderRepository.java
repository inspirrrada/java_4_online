package ua.com.alevel.persistence.repository.order;

import org.springframework.data.jpa.repository.Query;
import ua.com.alevel.persistence.entity.order.Order;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.List;

public interface OrderRepository extends BaseRepository<Order> {

    List<Order> findAllByUser(User user);
    @Query(value = "select user_id from orders where orders.id = ?1", nativeQuery = true)
    Long findUserIdByOrderId(Long orderId);
}
