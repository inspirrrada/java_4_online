package ua.com.alevel.persistence.repository.order;

import ua.com.alevel.persistence.entity.order.Order;
import ua.com.alevel.persistence.entity.order.OrderItem;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.List;

public interface OrderItemRepository extends BaseRepository<OrderItem> {

    List<OrderItem> findAllByOrder(Order order);
}
