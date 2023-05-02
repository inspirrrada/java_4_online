package ua.com.alevel.persistence.entity.sunglasses;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.type.LensType;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "lens_categories")
public class LensCategory extends BaseEntity {

    @Column(name = "lens_category", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private LensType lensType;

    public LensCategory() {
        super();
    }
}
