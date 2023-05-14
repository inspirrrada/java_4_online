package ua.com.alevel.service.user;

import ua.com.alevel.persistence.entity.user.Personal;

public interface PersonalService {

    Personal findByEmail(String email);
    void changeInfo(Personal personal);
}
