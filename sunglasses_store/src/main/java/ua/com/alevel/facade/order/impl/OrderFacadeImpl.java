package ua.com.alevel.facade.order.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.order.OrderDetailsDto;
import ua.com.alevel.dto.user.PersonalOrdersDto;
import ua.com.alevel.facade.order.OrderFacade;
import ua.com.alevel.persistence.entity.order.Order;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.service.order.OrderService;
import ua.com.alevel.service.user.PersonalService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderFacadeImpl implements OrderFacade {

    private final OrderService orderService;
    private final PersonalService personalService;

    public OrderFacadeImpl(OrderService orderService, PersonalService personalService) {
        this.orderService = orderService;
        this.personalService = personalService;
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
}
