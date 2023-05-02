package ua.com.alevel.persistence.entity.sunglasses;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.type.SexType;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "sex_categories")
public class SexCategory extends BaseEntity {

    @Column(name = "sex_category", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private SexType sexType;

    public SexCategory() {
        super();
    }
}
