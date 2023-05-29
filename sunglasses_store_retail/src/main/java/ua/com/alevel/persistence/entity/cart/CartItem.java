package ua.com.alevel.persistence.entity.cart;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.sunglasses.Sunglasses;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "cart_items")
public class CartItem extends BaseEntity {

    @ManyToOne
    private Cart cart;

    //    @ManyToMany
//    @JoinTable(
//            name = "carts_sunglasses",
//            joinColumns = @JoinColumn(name = "cart_id"),
//            inverseJoinColumns = @JoinColumn(name = "sunglasses_id")
//    )
    @ManyToOne
    private Sunglasses sunglasses;

    private Integer quantity;
    private Boolean active;
}
