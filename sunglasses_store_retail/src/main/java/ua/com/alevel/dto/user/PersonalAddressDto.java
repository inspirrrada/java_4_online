package ua.com.alevel.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.persistence.entity.user.Personal;

@Getter
@Setter
@NoArgsConstructor
public class PersonalAddressDto {

    private Long id;
    private String zip;
    private String region;
    private String city;
    private String street;
    private String building;
    private String apartment;

    public PersonalAddressDto(Personal personal) {
        this.id = personal.getId();
        this.zip = personal.getZip();
        this.region = personal.getRegion();
        this.city = personal.getCity();
        this.street = personal.getStreet();
        this.building = personal.getBuilding();
        this.apartment = personal.getApartment();
    }
}
