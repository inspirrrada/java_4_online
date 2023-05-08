package ua.com.alevel.persistence.entity.sunglasses;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.type.ColorType;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table (name = "colors")
public class Color extends BaseEntity {

    @Column(unique = true)
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
