package ua.com.alevel.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.persistence.entity.user.Personal;

@Getter
@Setter
@NoArgsConstructor
public class PersonalInfoDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String birthDay;

    public PersonalInfoDto(Personal personal) {
        this.id = personal.getId();
        this.firstName = personal.getFirstName();
        this.lastName = personal.getLastName();
        this.phoneNumber = personal.getPhoneNumber();
        if (personal.getBirthDay() != null) {
            this.birthDay = personal.getBirthDay().toString();
        }
    }

    @Override
    public String toString() {
        return "PersonalInfoDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", birthDay=" + birthDay +
                '}';
    }
}
