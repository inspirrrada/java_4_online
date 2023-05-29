package ua.com.alevel.persistence.entity.sunglasses.features;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.type.sunglasses_features.LensType;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "lens_categories")
public class LensCategory extends BaseEntity {

    @Column(name = "lens_category", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private LensType lensType;

    public LensCategory() {
        super();
    }

    @Override
    public String toString() {
        return lensType.toString();
    }
}
