package ua.com.alevel.persistence.entity.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.cart.Cart;
import ua.com.alevel.persistence.entity.sunglasses.Sunglasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Entity
@Table(name = "order_items")
public class OrderItem extends BaseEntity {

    @ManyToOne
    private Order order;

    @ManyToOne
    private Sunglasses sunglasses;

    private Integer quantity;

    @Column(precision = 7, scale = 2)
    private BigDecimal price;
}
