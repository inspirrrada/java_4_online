package ua.com.alevel.dto.cart;

import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CartFormDto {

    private List<SunglassesCartDto> cartFormList;
    private int totalQty;
    private BigDecimal totalAmount;

    public CartFormDto(List<SunglassesCartDto> cartFormList) {
        this.cartFormList = cartFormList;
        this.totalQty = cartFormList.stream().mapToInt(SunglassesCartDto::getQty).sum();
        BigDecimal sum = BigDecimal.ZERO;
        for (SunglassesCartDto sunglassesCartDto : cartFormList) {
            sum = sum.add(sunglassesCartDto.getTotalPrice());
        }
        this.totalAmount = sum;
    }
}
