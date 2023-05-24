package ua.com.alevel.persistence.entity.sunglasses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.listener.SunglassesVisibleGenerationListener;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.sunglasses.brand.Brand;
import ua.com.alevel.persistence.entity.sunglasses.features.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Entity
@Table(name = "sunglasses")
@EntityListeners({
        SunglassesVisibleGenerationListener.class
})
public class Sunglasses extends BaseEntity {

    @Column(name = "model_code", nullable = false, unique = true)
    private String modelCode;

    @Column(name = "lens_width")
    private Integer lensWidth;

    @Column(name = "bridge_width")
    private Integer bridgeWidth;

    @Column(name = "temple_length")
    private Integer templeLength;

    @Column(name = "image_url1")
    private String imageUrl1;

    @Column(name = "image_url2")
    private String imageUrl2;

    @Column(name = "image_url3")
    private String imageUrl3;

    @Column(name = "image_url4")
    private String imageUrl4;

    @Column(precision = 7, scale = 2)
    private BigDecimal price;

    private Integer quantity;

    @ManyToOne
    private Brand brand;

    @ManyToOne
    private Color color;

    @ManyToOne
    private SexCategory sexCategory;

    @ManyToOne
    private FrameShape frameShape;

    @ManyToOne
    private FrameMaterial frameMaterial;

    @ManyToOne
    private LensMaterial lensMaterial;

    @ManyToOne
    private LensCategory lensCategory;
}
