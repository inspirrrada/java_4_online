package ua.com.alevel.entity.sunglasses.features;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.type.sunglasses_features.LensMaterialType;
import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "lens_materials")
public class LensMaterial extends BaseEntity {

    @Column(name = "lens_material", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private LensMaterialType lensMaterialType;

    public LensMaterial() {
        super();
    }

    @Override
    public String toString() {
        return lensMaterialType.toString();
    }
}
