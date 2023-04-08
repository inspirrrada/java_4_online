package ua.com.alevel.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer accountsQty;
}
