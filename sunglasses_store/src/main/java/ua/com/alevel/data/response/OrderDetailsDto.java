package ua.com.alevel.data.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.type.PaymentsType;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderDetailsDto {

    private Long id;
    private String contactFirstName;
    private String contactLastName;
    private Integer contactPhoneNumber;
    private Integer reservePhoneNumber;
    private String deliveryZip;
    private String deliveryRegion;
    private String deliveryCity;
    private String deliveryStreet;
    private String deliveryBuilding;
    private String deliveryApartment;
    private PaymentsType paymentMethod;

    public OrderDetailsDto(Personal personal) {
        this.id = personal.getId();
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
}
