package ua.com.alevel.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name="sneakers")
public class Sneaker extends BaseEntity {

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Integer size;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name="photo_url", nullable = false)
    private String photoUrl;

    @Column(precision = 7, scale = 2)
    private BigDecimal price;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany(mappedBy = "sneakers")
    private Set<Brand> brands;

    @ManyToOne
    private Manufacturer manufacturer;

    public Sneaker() {
        super();
        this.price = new BigDecimal("00.00");
        this.quantity = 0;
        this.brands = new HashSet<>();
    }
}
