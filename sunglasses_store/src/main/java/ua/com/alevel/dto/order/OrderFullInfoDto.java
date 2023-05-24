package ua.com.alevel.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.persistence.entity.order.Order;
import ua.com.alevel.persistence.type.order.OrderStatusType;

@Getter
@Setter
@NoArgsConstructor
public class OrderFullInfoDto extends OrderDetailsDto {

    private String number;
    private OrderStatusType status;

    public OrderFullInfoDto(Order order) {
        super(order);
        this.number = order.getNumber();
        this.status = order.getOrderStatus();
    }
}
