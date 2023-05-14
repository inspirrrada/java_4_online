package ua.com.alevel.data.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.user.Personal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PersonalInfoDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String email;
    private Integer phoneNumber;
    private OffsetDateTime birthDay;

    public PersonalInfoDto(Personal personal) {
        this.id = personal.getId();
        this.firstName = personal.getFirstName();
        this.lastName = personal.getLastName();
        this.fatherName = personal.getFatherName();
        this.email = personal.getEmail();
        this.phoneNumber = personal.getPhoneNumber();
        this.birthDay = personal.getBirthDay();
    }

    @Override
    public String toString() {
        return "PersonalInfoDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", birthDay=" + birthDay +
                '}';
    }
}
