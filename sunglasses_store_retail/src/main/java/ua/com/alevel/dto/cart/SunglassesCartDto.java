package ua.com.alevel.dto.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.dto.sunglasses.SunglassesPLPDto;
import ua.com.alevel.persistence.entity.cart.CartItem;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class SunglassesCartDto extends SunglassesPLPDto {

    private Long cartItemId;
    private Integer qty;
    private boolean shouldBeRemoved;
    private BigDecimal totalPrice;

    public SunglassesCartDto(CartItem cartItem) {
        super(cartItem.getSunglasses());
        this.cartItemId = cartItem.getId();
        this.qty = cartItem.getQuantity();
        this.shouldBeRemoved = false;
        this.totalPrice = cartItem.getSunglasses().getPrice().multiply(new BigDecimal(this.qty));
    }
}
