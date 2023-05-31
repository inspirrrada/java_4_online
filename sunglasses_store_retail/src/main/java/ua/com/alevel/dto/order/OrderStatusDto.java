package ua.com.alevel.dto.order;

import lombok.*;
import ua.com.alevel.dto.user.PersonalOrdersDto;
import ua.com.alevel.persistence.entity.order.Order;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.type.order.OrderStatusType;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderStatusDto extends PersonalOrdersDto {

    private Long id;
    private String userEmail;
    private OrderStatusType orderStatusType;

    public OrderStatusDto(Order order, User user) {
        super(order);
        this.id = order.getId();
        this.orderStatusType = order.getOrderStatus();
        this.userEmail = user.getEmail();
    }
}
