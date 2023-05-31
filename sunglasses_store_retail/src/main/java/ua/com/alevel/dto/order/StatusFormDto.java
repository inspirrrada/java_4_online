package ua.com.alevel.dto.order;

import lombok.*;
import ua.com.alevel.dto.cart.SunglassesCartDto;
import ua.com.alevel.persistence.entity.order.Order;
import ua.com.alevel.persistence.type.order.OrderStatusType;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Data
public class StatusFormDto {

    private List<OrderStatusDto> orderStatusDtoList;

    public StatusFormDto(List<OrderStatusDto> oderStatusDtoList) {
        this.orderStatusDtoList = oderStatusDtoList;
    }
}
