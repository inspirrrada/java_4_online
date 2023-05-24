package ua.com.alevel.service.order.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.order.Order;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.order.OrderRepository;
import ua.com.alevel.service.order.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }
}
