package ua.com.alevel.data.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.persistence.entity.cart.CartItem;
import ua.com.alevel.persistence.entity.sunglasses.Sunglasses;

@Getter
@Setter
@NoArgsConstructor
public class SunglassesCartDto extends SunglassesPLPDto {

//    private Long id;
//    private String modelName;
//    private String imageUrl1;
//    private String price;
    private Integer qty;
    private boolean shouldBeRemoved;

    public SunglassesCartDto(CartItem cartItem) {
        super(cartItem.getSunglasses());
//        this.imageUrl1 = cartItem.getSunglasses().getImageUrl1();
        this.qty = cartItem.getQuantity();
        this.shouldBeRemoved = false;
    }
}
