package ua.com.alevel.persistence.entity.sunglasses;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.brand.Brand;
import ua.com.alevel.persistence.type.SexType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "sunglasses")
public class Sunglasses extends BaseEntity {

    @Column(name = "model_code", nullable = false, unique = true)
    private String modelCode;

    @Column(name = "color", nullable = false, unique = true)
    private String color;

    @Column(name = "lens_width")
    private Integer lensWidth;

    @Column(name = "bridge_width")
    private Integer bridgeWidth;

    @Column(name = "temple_length")
    private Integer templeLength;

    @Column(precision = 7, scale = 2)
    private BigDecimal price;

    private Integer quantity;

    @ManyToOne
    private Brand brand;

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
