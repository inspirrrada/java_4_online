package ua.com.alevel.persistence.entity.cart;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.data.response.SunglassesCartDto;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.sunglasses.Sunglasses;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "cart_item_register")
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
}
