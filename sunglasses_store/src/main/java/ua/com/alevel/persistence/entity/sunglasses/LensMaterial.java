package ua.com.alevel.persistence.entity.sunglasses;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.type.LensMaterialType;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "lens_materials")
public class LensMaterial extends BaseEntity {

    @Column(name = "lens_material", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private LensMaterialType lensMaterial;

    public LensMaterial() {
        super();
    }
}
