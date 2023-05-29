package ua.com.alevel.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.persistence.entity.user.Personal;

@Getter
@Setter
@NoArgsConstructor
public class PersonalPasswordDto {

    private Long id;
    private String passwordOld;
    private String passwordOldCheck;
    private String passwordNew;
    private String passwordNewRepeat;

    public PersonalPasswordDto(Personal personal) {
        this.id = personal.getId();
        this.passwordOld = personal.getPassword();
    }
}
