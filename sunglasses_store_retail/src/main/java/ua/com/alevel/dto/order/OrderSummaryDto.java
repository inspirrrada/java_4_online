package ua.com.alevel.dto.order;

import lombok.*;
import ua.com.alevel.dto.cart.SunglassesCartDto;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Data
public class OrderSummaryDto {

    private List<SunglassesOrderDto> sunglassesOrderDtoList;
    private int totalQty;
    private BigDecimal totalAmount;

    public OrderSummaryDto(List<SunglassesOrderDto> sunglassesOrderDtoList) {
        this.sunglassesOrderDtoList = sunglassesOrderDtoList;
        this.totalQty = sunglassesOrderDtoList.stream().mapToInt(SunglassesOrderDto::getQty).sum();
        BigDecimal sum = BigDecimal.ZERO;
        for (SunglassesOrderDto sunglassesOrderDto : sunglassesOrderDtoList) {
            sum = sum.add(sunglassesOrderDto.getTotalPrice());
        }
        this.totalAmount = sum;
    }
}
