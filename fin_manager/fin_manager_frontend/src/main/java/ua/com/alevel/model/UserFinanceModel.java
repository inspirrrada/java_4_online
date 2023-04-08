package ua.com.alevel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class UserFinanceModel extends UserModel {

    private Collection<AccountModel> accounts;
}
