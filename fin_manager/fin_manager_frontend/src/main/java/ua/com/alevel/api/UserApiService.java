package ua.com.alevel.api;

import ua.com.alevel.model.UserFinanceModel;
import ua.com.alevel.model.UserModel;

import java.util.Collection;
import java.util.Optional;

public interface UserApiService {

    //+
    Collection<UserModel> findAll();
    Optional<UserFinanceModel> findById(Long id);
}
