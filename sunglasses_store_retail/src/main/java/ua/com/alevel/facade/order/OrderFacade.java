package ua.com.alevel.facade.order;

import ua.com.alevel.dto.order.OrderDetailsDto;
import ua.com.alevel.dto.user.PersonalOrdersDto;
import ua.com.alevel.persistence.entity.order.Order;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.entity.user.User;

import java.util.List;
import java.util.Set;

public interface OrderFacade {

    boolean saveOrderDetails(OrderDetailsDto orderDetailsDto);
    Set<PersonalOrdersDto> findAllOrdersByUser(User user);
    OrderDetailsDto showOrderDetails(Personal personal);
    void createNewOrder(OrderDetailsDto orderDetailsDto, Personal personal);
}
