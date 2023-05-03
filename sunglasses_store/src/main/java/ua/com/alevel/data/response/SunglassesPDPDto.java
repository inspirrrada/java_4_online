package ua.com.alevel.data.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.persistence.entity.brand.Brand;
import ua.com.alevel.persistence.entity.sunglasses.*;

@Getter
@Setter
@NoArgsConstructor
public class SunglassesPDPDto extends SunglassesPLPDto {

    private SexCategory sexCategory;
    private Brand brand;
    private FrameShape frameShape;
    private FrameMaterial frameMaterial;
    private LensCategory lensCategory;
    private LensMaterial lensMaterial;
    private Integer lengthWidth;
    private Integer templeLength;
    private Integer bridgeWidth;

    public SunglassesPDPDto(Sunglasses sunglasses) {
        super(sunglasses);
        this.sexCategory = sunglasses.getSexCategory();
        this.brand = sunglasses.getBrand();
        this.frameShape = sunglasses.getFrameShape();
        this.frameMaterial = sunglasses.getFrameMaterial();
        this.lensCategory = sunglasses.getLensCategory();
        this.lensMaterial = sunglasses.getLensMaterial();
        this.lengthWidth = sunglasses.getLensWidth();
        this.templeLength = sunglasses.getTempleLength();
        this.bridgeWidth = sunglasses.getBridgeWidth();
    }
}
