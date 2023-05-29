package ua.com.alevel.entity.sunglasses.features;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.type.sunglasses_features.FrameShapeType;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "frame_shapes")
public class FrameShape extends BaseEntity {

    @Column(name = "frame_shape", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private FrameShapeType frameShapeType;

    public FrameShape() {
        super();
    }

    @Override
    public String toString() {
        return frameShapeType.toString();
    }
}
