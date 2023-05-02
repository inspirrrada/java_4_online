package ua.com.alevel.persistence.entity.sunglasses;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.type.FrameShapeType;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "frame_shapes")
public class FrameShape extends BaseEntity {

    @Column(name = "frame_shape", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private FrameShapeType frameShape;

    public FrameShape() {
        super();
    }
}
