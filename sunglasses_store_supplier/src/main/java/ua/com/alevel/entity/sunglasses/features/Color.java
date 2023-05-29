package ua.com.alevel.entity.sunglasses.features;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.type.sunglasses_features.ColorType;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table (name = "colors")
public class Color extends BaseEntity {

    @Column(name="color", unique = true)
    @Enumerated(EnumType.STRING)
    private ColorType colorType;

    public Color() {
        super();
    }

    @Override
    public String toString() {
        return colorType.toString();
    }
}
