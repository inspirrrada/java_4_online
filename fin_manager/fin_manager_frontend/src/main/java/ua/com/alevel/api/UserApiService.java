package ua.com.alevel.api;

import ua.com.alevel.model.UserAccountsModel;
import ua.com.alevel.model.UserModel;

import java.util.Collection;
import java.util.Optional;

public interface UserApiService {

    //+
    Collection<UserModel> findAll();
//    Optional<UserAccountsModel> findById(Long id);
}
