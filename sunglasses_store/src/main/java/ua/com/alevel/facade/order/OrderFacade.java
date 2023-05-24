package ua.com.alevel.facade.order;

import ua.com.alevel.dto.order.OrderDetailsDto;

public interface OrderFacade {

    boolean saveOrderDetails(OrderDetailsDto orderDetailsDto);
}
