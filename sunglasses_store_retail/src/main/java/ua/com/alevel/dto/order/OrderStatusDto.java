package ua.com.alevel.dto.order;

import lombok.*;
import ua.com.alevel.dto.user.PersonalOrdersDto;
import ua.com.alevel.persistence.entity.order.Order;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.type.order.OrderStatusType;
import ua.com.alevel.persistence.type.order.PaymentMethodType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
public class OrderStatusDto extends PersonalOrdersDto {

    private Long id;
    private String userEmail;
    private OrderStatusType orderStatusType;

//    public OrderStatusDto(Order order, Personal personal) {
//        super(order);
//        this.id = order.getId();
//        this.userEmail = personal.getEmail();
//    }

    public OrderStatusDto(Order order) {
        super(order);
        this.id = order.getId();
        this.orderStatusType = order.getOrderStatus();
    }

    public OrderStatusDto() {
    }
}
