package ua.com.alevel.entity.sunglasses.brand;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {

    @Column(name = "brand_name", nullable = false, unique = true)
    private String brandName;

//    @OneToMany
//    Set<Sunglasses> sunglasses;

    public Brand() {
        super();
//        sunglasses = new HashSet<>();
    }

    @Override
    public String toString() {
        return brandName;
    }
}
