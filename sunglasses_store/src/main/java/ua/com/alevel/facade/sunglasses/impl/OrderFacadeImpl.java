package ua.com.alevel.facade.sunglasses.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.data.response.OrderDetailsDto;
import ua.com.alevel.facade.sunglasses.OrderFacade;
import ua.com.alevel.persistence.entity.order.Order;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.service.user.PersonalService;

@Service
public class OrderFacadeImpl implements OrderFacade {

    private final PersonalService personalService;

    public OrderFacadeImpl(PersonalService personalService) {
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
}
