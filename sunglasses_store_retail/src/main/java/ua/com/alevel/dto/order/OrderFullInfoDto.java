package ua.com.alevel.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.dto.cart.SunglassesCartDto;
import ua.com.alevel.persistence.entity.order.Order;
import ua.com.alevel.persistence.entity.order.OrderItem;
import ua.com.alevel.persistence.type.order.OrderStatusType;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class OrderFullInfoDto extends OrderDetailsDto {

    private String number;
    private OrderStatusType status;
    private Integer totalQty;
    private BigDecimal totalAmount;

    public OrderFullInfoDto(Order order) {
        super(order);
        this.number = order.getNumber();
        this.status = order.getOrderStatus();
        this.totalQty = order.getOrderItems().stream().mapToInt(OrderItem::getQuantity).sum();
        this.totalAmount = order.getTotalAmount();
    }
}
