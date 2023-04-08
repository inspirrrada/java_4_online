package ua.com.alevel.dto;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.User;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer accountsQty;

    public UserDTO() {}

    public UserDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.accountsQty =user.getAccountsQty();
    }
}
