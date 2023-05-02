package ua.com.alevel.persistence.entity.sunglasses;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.type.FrameMaterialType;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "frame_materials")
public class FrameMaterial extends BaseEntity {

    @Column(name = "frame_material", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private FrameMaterialType frameMaterial;

    public FrameMaterial() {
        super();
    }
}
