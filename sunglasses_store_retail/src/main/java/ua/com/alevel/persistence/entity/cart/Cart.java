package ua.com.alevel.persistence.entity.cart;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.user.User;
import javax.persistence.*;

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
}
