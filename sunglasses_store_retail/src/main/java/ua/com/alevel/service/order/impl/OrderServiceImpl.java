package ua.com.alevel.service.order.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.cart.SunglassesCartDto;
import ua.com.alevel.persistence.entity.cart.Cart;
import ua.com.alevel.persistence.entity.cart.CartItem;
import ua.com.alevel.persistence.entity.order.Order;
import ua.com.alevel.persistence.entity.order.OrderItem;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.cart.CartItemRepository;
import ua.com.alevel.persistence.repository.order.OrderItemRepository;
import ua.com.alevel.persistence.repository.order.OrderRepository;
import ua.com.alevel.service.order.OrderService;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CartItemRepository cartItemRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public List<Order> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Long findUserIdByOrderId(Long orderId) {
        return orderRepository.findUserIdByOrderId(orderId);
    }

    @Override
    public void createNewOrder(Order order, Cart cart) {
        orderRepository.save(order);
        order.setNumber("SB-000" + order.getId());
        Collection<CartItem> cartItems = cartItemRepository.findAllByCart(cart);
        Set<OrderItem> orderItems = new HashSet<>();
        cartItems.stream().forEach(v -> {
           /* OrderItem orderItem = new OrderItem();
            +orderItem.setOrder(order);
            orderItem.setQuantity(v.getQuantity());
            orderItem.setSunglasses(v.getSunglasses());
            orderItem.setPrice(v.getSunglasses().getPrice());*/
            OrderItem orderItem = convertCartItemToOrderItem(v);
            orderItem.setOrder(order);
            orderItems.add(orderItem);
            orderItemRepository.save(orderItem);
        });
        order.setOrderItems(orderItems);
        BigDecimal sum = BigDecimal.ZERO;
        for (OrderItem orderItem : orderItems) {
            BigDecimal totalPrice = orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity()));
            sum = sum.add(totalPrice);
        }
       order.setTotalAmount(sum);
        cartItemRepository.deleteAll(cartItems);
    }

    private OrderItem convertCartItemToOrderItem(CartItem cartItem) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setSunglasses(cartItem.getSunglasses());
        orderItem.setPrice(cartItem.getSunglasses().getPrice());
        return orderItem;
    }
}
