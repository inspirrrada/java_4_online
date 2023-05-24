package ua.com.alevel.persistence.entity.sunglasses.features;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.type.sunglasses_features.SexType;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "sex_categories")
public class SexCategory extends BaseEntity {

    @Column(name = "sex_category", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private SexType sexType;

    public SexCategory() {
        super();
    }

    @Override
    public String toString() {
        return sexType.toString();
    }
}
