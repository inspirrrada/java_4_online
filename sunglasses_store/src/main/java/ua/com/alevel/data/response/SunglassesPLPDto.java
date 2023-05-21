package ua.com.alevel.data.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.persistence.entity.sunglasses.Sunglasses;

@Getter
@Setter
@NoArgsConstructor
public class SunglassesPLPDto {

    private Long id;
    private String modelName;
    private String imageUrl1;
    private String price;
    private Boolean visible;

    public SunglassesPLPDto(Sunglasses sunglasses) {
        this.id = sunglasses.getId();
        this.modelName = (sunglasses.getBrand().toString() + " "  + sunglasses.getModelCode() + " " + sunglasses.getColor().getColorType().getValue()).toUpperCase();
        this.imageUrl1 = sunglasses.getImageUrl1();
        this.price = sunglasses.getPrice().toString();
        this.visible = sunglasses.getVisible();
    }
}
