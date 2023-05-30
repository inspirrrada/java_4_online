package ua.com.alevel.facade.order.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.cart.CartFormDto;
import ua.com.alevel.dto.cart.SunglassesCartDto;
import ua.com.alevel.dto.order.OrderDetailsDto;
import ua.com.alevel.dto.order.OrderStatusDto;
import ua.com.alevel.dto.order.OrderSummaryDto;
import ua.com.alevel.dto.order.SunglassesOrderDto;
import ua.com.alevel.dto.user.PersonalOrdersDto;
import ua.com.alevel.facade.order.OrderFacade;
import ua.com.alevel.persistence.entity.cart.Cart;
import ua.com.alevel.persistence.entity.cart.CartItem;
import ua.com.alevel.persistence.entity.order.Order;
import ua.com.alevel.persistence.entity.order.OrderItem;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.type.order.OrderStatusType;
import ua.com.alevel.service.cart.CartService;
import ua.com.alevel.service.order.OrderService;
import ua.com.alevel.service.user.PersonalService;
import ua.com.alevel.service.user.UserService;

import java.util.*;

@Service
public class OrderFacadeImpl implements OrderFacade {

    private final OrderService orderService;
    private final UserService userService;
    private final CartService cartService;

    public OrderFacadeImpl(OrderService orderService, PersonalService personalService, UserService userService, CartService cartService) {
        this.orderService = orderService;
        this.userService = userService;
        this.cartService = cartService;
    }

    @Override
    public boolean saveOrderDetails(OrderDetailsDto orderDetailsDto) {
//        Long id = orderDetailsDto.getId();
//        Order order = new Order();
//        order.setContactFirstName(or);
//        try {
//            orderService.saveOrder(personalCurrent);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
        return true;
    }

    @Override
    public Set<PersonalOrdersDto> findAllOrdersByUser(User user) {
        Set<PersonalOrdersDto> personalOrders = new HashSet<>();
        orderService.findAllByUser(user)
                .forEach(order -> {
                    personalOrders.add(new PersonalOrdersDto(order));
                });
        return personalOrders;
    }

    @Override
    public OrderDetailsDto showOrderDetails(Personal personal) {
        return new OrderDetailsDto(personal);
    }

    @Override
    public OrderDetailsDto showOrderDetails(Order order) {
        return new OrderDetailsDto(order);
    }

    @Override
    public void createNewOrder(OrderDetailsDto orderDetailsDto, Personal personal) {
        Order order = convertOrderDetailsDtoToOrder(orderDetailsDto);
        order.setUser(personal);
        Cart cart = cartService.findById(personal.getId());
        orderService.createNewOrder(order, cart);
    }

    @Override
    public List<OrderStatusDto> getOrdersInfoForAdmin() {
        List<Order> orders = orderService.findAll();
        System.out.println("order: " + orders);
        List<OrderStatusDto> orderStatusDtoList = new ArrayList<>();
        orders.forEach(v -> {
            OrderStatusDto orderStatusDto = convertOrderToOrderStatusDto((Order)v);
            orderStatusDtoList.add(orderStatusDto);
        });
        return orderStatusDtoList;
    }

    @Override
    public User findUserByOrderId(Order order) {
        Long orderId = order.getId();
        Long userId = orderService.findUserIdByOrderId(orderId);
        User user = userService.findById(userId);
        return user;
    }

    @Override
    public Order findById(Long id) {
        return orderService.findById(id);
    }

    @Override
    public OrderSummaryDto findAllByOrder(Long orderId) {
        Collection<OrderItem> orderItems = orderService.findAllByOrder(orderId);
        return new OrderSummaryDto(orderItems
                .stream()
                .map(SunglassesOrderDto::new)
                .toList());
    }

    private Order convertOrderDetailsDtoToOrder(OrderDetailsDto orderDetailsDto) {
        Order order = new Order();
        order.setContactFirstName(orderDetailsDto.getContactFirstName());
        order.setContactLastName(orderDetailsDto.getContactLastName());
        order.setContactPhoneNumber(orderDetailsDto.getContactPhoneNumber());
        order.setReservePhoneNumber(orderDetailsDto.getReservePhoneNumber());
        order.setDeliveryZip(orderDetailsDto.getDeliveryZip());
        order.setDeliveryRegion(orderDetailsDto.getDeliveryRegion());
        order.setDeliveryCity(orderDetailsDto.getDeliveryCity());
        order.setDeliveryStreet(orderDetailsDto.getDeliveryStreet());
        order.setDeliveryBuilding(orderDetailsDto.getDeliveryBuilding());
        order.setDeliveryApartment(orderDetailsDto.getDeliveryApartment());
        order.setOrderStatus(OrderStatusType.CREATED);
        order.setOrderNotes(orderDetailsDto.getNotes());
        order.setPaymentMethod(orderDetailsDto.getPaymentMethod());
        return order;
    }

    private OrderStatusDto convertOrderToOrderStatusDto(Order order) {
        OrderStatusDto orderStatusDto = new OrderStatusDto();
        orderStatusDto.setId(order.getId());
        orderStatusDto.setCreated(order.getCreated());
        orderStatusDto.setNumber(order.getNumber());
        orderStatusDto.setStatus(order.getOrderStatus());
        orderStatusDto.setTotalAmount(order.getTotalAmount());
        System.out.println("totalAmount: " + order.getTotalAmount());
        User user = findUserByOrderId(order);
        orderStatusDto.setUserEmail(user.getEmail());
        return orderStatusDto;
    }
}
