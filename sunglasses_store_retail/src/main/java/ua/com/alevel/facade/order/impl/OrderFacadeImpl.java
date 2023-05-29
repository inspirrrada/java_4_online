package ua.com.alevel.facade.order.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.order.OrderDetailsDto;
import ua.com.alevel.dto.user.PersonalOrdersDto;
import ua.com.alevel.facade.order.OrderFacade;
import ua.com.alevel.persistence.entity.cart.Cart;
import ua.com.alevel.persistence.entity.order.Order;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.type.order.OrderStatusType;
import ua.com.alevel.service.cart.CartService;
import ua.com.alevel.service.order.OrderService;
import ua.com.alevel.service.user.PersonalService;

import java.util.HashSet;
import java.util.Set;

@Service
public class OrderFacadeImpl implements OrderFacade {

    private final OrderService orderService;
    private final PersonalService personalService;
    private final CartService cartService;

    public OrderFacadeImpl(OrderService orderService, PersonalService personalService, CartService cartService) {
        this.orderService = orderService;
        this.personalService = personalService;
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
    public void createNewOrder(OrderDetailsDto orderDetailsDto, Personal personal) {
        Order order = convertOrderDetailsDtoToOrder(orderDetailsDto);
        order.setUser(personal);
        Cart cart = cartService.findById(personal.getId());
        orderService.createNewOrder(order, cart);
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
}
