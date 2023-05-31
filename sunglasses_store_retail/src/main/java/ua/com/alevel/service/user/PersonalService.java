package ua.com.alevel.service.user;

import ua.com.alevel.persistence.entity.user.Personal;


public interface PersonalService {

    Personal findByEmail(String email);
    boolean existsByEmail(String email);
    Personal findById(Long id);
    void createAccount(Personal personal);
    void changeInfo(Personal personalUpdated);
}
