package ua.com.alevel.data.response;

import lombok.*;
import ua.com.alevel.persistence.entity.cart.CartItem;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Data
public class CartFormDto {

    private List<SunglassesCartDto> cartFormList;

    public CartFormDto(List<SunglassesCartDto> cartFormList) {
        this.cartFormList = cartFormList;
    }
}
