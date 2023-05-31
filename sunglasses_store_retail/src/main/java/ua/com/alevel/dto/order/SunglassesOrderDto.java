package ua.com.alevel.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.dto.sunglasses.SunglassesPLPDto;
import ua.com.alevel.persistence.entity.order.OrderItem;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class SunglassesOrderDto extends SunglassesPLPDto {

    private Long cartItemId;
    private Integer qty;
    private boolean shouldBeRemoved;
    private BigDecimal totalPrice;

    public SunglassesOrderDto(OrderItem orderItem) {
        super(orderItem.getSunglasses());
        this.cartItemId = orderItem.getId();
        this.qty = orderItem.getQuantity();
        this.shouldBeRemoved = false;
        this.totalPrice = orderItem.getSunglasses().getPrice().multiply(new BigDecimal(this.qty));
    }
}
