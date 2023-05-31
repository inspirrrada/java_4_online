package ua.com.alevel.dto.order;

import lombok.*;
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
