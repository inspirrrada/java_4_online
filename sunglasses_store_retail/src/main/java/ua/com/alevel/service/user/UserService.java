package ua.com.alevel.service.user;

import ua.com.alevel.persistence.entity.user.User;

public interface UserService {

    User findById(Long id);
}
