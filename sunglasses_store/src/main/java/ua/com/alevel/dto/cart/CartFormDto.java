package ua.com.alevel.dto.cart;

import lombok.*;

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
