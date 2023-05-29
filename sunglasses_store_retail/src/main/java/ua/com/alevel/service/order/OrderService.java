package ua.com.alevel.service.order;

import ua.com.alevel.persistence.entity.cart.Cart;
import ua.com.alevel.persistence.entity.order.Order;
import ua.com.alevel.persistence.entity.user.User;

import java.util.List;

public interface OrderService {

    List<Order> findAllByUser(User user);
    void createNewOrder(Order order, Cart cart);
}
