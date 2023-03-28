package ua.com.alevel.persistence.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name="brands")
public class Brand extends BaseEntity {

    @Column(name="brand_name")
    private String brandName;

    @Enumerated(EnumType.STRING)
    private CountryCode country;

    @ManyToMany
    @JoinTable(
            name = "brand_sneaker",
            joinColumns = @JoinColumn(name = "brand_id"),
            inverseJoinColumns = @JoinColumn(name = "sneaker_id")
    )
    private Set<Sneaker> sneakers;

    public Brand() {
        super();
        this.sneakers = new HashSet<>();
    }
}
