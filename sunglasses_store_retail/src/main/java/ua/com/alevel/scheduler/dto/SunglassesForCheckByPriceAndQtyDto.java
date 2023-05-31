package ua.com.alevel.scheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SunglassesForCheckByPriceAndQtyDto {

    private String modelCode;
    private BigDecimal price;
    private Integer quantity;
}
