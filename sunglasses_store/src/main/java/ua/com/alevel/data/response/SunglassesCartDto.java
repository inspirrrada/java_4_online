package ua.com.alevel.data.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.cart.CartItem;
import ua.com.alevel.persistence.entity.sunglasses.Sunglasses;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class SunglassesCartDto extends SunglassesPLPDto {

//    private Long id;
//    private String modelName;
//    private String imageUrl1;
//    private String price;
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

    @Override
    public String toString() {
        return "SunglassesCartDto{" +
                "id=" + cartItemId +
                ", qty=" + qty +
                ", shouldBeRemoved=" + shouldBeRemoved +
                ", id super=" + getId() +
                ", modelName=" + getModelName() +
                ", imageUrl=" + getImageUrl1() +
                ", price=" + getPrice() +
                "} " + super.toString();
    }
}
