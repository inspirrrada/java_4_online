package ua.com.alevel.persistence.entity.order;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.type.order.OrderStatusType;
import ua.com.alevel.persistence.type.order.PaymentMethodType;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    private String number;

    @ManyToOne
    private User user;

    @OneToMany
    private Set<OrderItem> orderItems;

    @Column(name="contact_first_name")
    private String contactFirstName;

    @Column(name="contact_last_name")
    private String contactLastName;

    @Column(name="contact_phone_number")
    private Integer contactPhoneNumber;

    @Column(name="reserve_phone_number")
    private Integer reservePhoneNumber;

    @Column(name="delivery_region")
    private String deliveryRegion;

    @Column(name="delivery_city")
    private String deliveryCity;

    @Column(name="delivery_zip")
    private String deliveryZip;

    @Column(name="delivery_street")
    private String deliveryStreet;

    @Column(name="delivery_building")
    private String deliveryBuilding;

    @Column(name="delivery_apartment")
    private String deliveryApartment;

    @Enumerated(EnumType.STRING)
    @Column(name="payment_method")
    private PaymentMethodType paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name="order_status")
    private OrderStatusType orderStatus;

    @Column(name="order_notes")
    private String orderNotes;

    @Column(precision = 7, scale = 2, name="total_amount")
    private BigDecimal totalAmount;
}
