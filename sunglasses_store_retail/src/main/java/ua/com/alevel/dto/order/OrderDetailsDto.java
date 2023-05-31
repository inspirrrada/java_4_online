package ua.com.alevel.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.order.Order;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.type.order.PaymentMethodType;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderDetailsDto {

    private Long id;
    private String contactFirstName;
    private String contactLastName;
    private String contactPhoneNumber;
    private String reservePhoneNumber;
    private String deliveryZip;
    private String deliveryRegion;
    private String deliveryCity;
    private String deliveryStreet;
    private String deliveryBuilding;
    private String deliveryApartment;
    private String notes;
    private PaymentMethodType paymentMethod;

    public OrderDetailsDto(Personal personal) {
        this.contactFirstName = personal.getFirstName();
        this.contactLastName = personal.getLastName();
        this.contactPhoneNumber = personal.getPhoneNumber();
        this.deliveryZip = personal.getZip();
        this.deliveryRegion = personal.getRegion();
        this.deliveryCity = personal.getCity();
        this.deliveryStreet = personal.getStreet();
        this.deliveryBuilding = personal.getBuilding();
        this.deliveryApartment = personal.getApartment();
    }

    public OrderDetailsDto(Order order) {
        this.id = order.getId();
        this.contactFirstName = order.getContactFirstName();
        this.contactLastName = order.getContactLastName();
        this.contactPhoneNumber = order.getContactPhoneNumber();
        this.deliveryZip = order.getDeliveryZip();
        this.deliveryRegion = order.getDeliveryRegion();
        this.deliveryCity = order.getDeliveryCity();
        this.deliveryStreet = order.getDeliveryStreet();
        this.deliveryBuilding = order.getDeliveryBuilding();
        this.deliveryApartment = order.getDeliveryApartment();
    }
}
