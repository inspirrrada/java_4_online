package ua.com.alevel.entity.sunglasses.features;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.type.sunglasses_features.FrameMaterialType;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "frame_materials")
public class FrameMaterial extends BaseEntity {

    @Column(name = "frame_material", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private FrameMaterialType frameMaterialType;

    public FrameMaterial() {
        super();
    }

    @Override
    public String toString() {
        return frameMaterialType.toString();
    }
}
