package ua.com.alevel.persistence.entity.cart;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.sunglasses.Sunglasses;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.entity.user.User;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

//    @ManyToMany
//    @JoinTable(
//            name = "carts_sunglasses",
//            joinColumns = @JoinColumn(name = "cart_id"),
//            inverseJoinColumns = @JoinColumn(name = "sunglasses_id")
//    )
//    @OneToMany
//    private Set<CartItem> cartItems;
}
