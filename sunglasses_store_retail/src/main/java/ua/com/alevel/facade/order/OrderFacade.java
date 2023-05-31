package ua.com.alevel.facade.order;

import ua.com.alevel.dto.order.OrderDetailsDto;
import ua.com.alevel.dto.order.OrderStatusDto;
import ua.com.alevel.dto.order.OrderSummaryDto;
import ua.com.alevel.dto.order.StatusFormDto;
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
    OrderDetailsDto showOrderDetails(Order order);
    void createNewOrder(OrderDetailsDto orderDetailsDto, Personal personal);
    List<OrderStatusDto> getOrdersInfoForAdmin();
    User findUserByOrderId(Order order);
    Order findById(Long id);
    OrderSummaryDto findAllByOrder(Long orderId);
    List<OrderStatusDto> findAllOrderStatusDto();
    void updateOrdersStatuses(StatusFormDto statusFormDto);
}
