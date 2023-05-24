package ua.com.alevel.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.order.Order;
import ua.com.alevel.persistence.type.order.OrderStatusType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
public class OrdersHistoryDto {

    private Long id;
    private OffsetDateTime created;
    private String number;
    private OrderStatusType status;
    private BigDecimal totalAmount;

    public OrdersHistoryDto(Order order) {
        this.id = order.getId();
        this.created = order.getCreated();
        this.number = order.getNumber();
        this.status = order.getOrderStatus();
    }
}
