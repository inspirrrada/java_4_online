package ua.com.alevel.persistence.repository.order;

import ua.com.alevel.persistence.entity.order.Order;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.List;

public interface OrderRepository extends BaseRepository<Order> {

    List<Order> findAllByUser(User user);
}
