package ua.com.alevel.service.user;

import ua.com.alevel.persistence.entity.user.Personal;

import java.util.Optional;

public interface PersonalService {

    Personal findByEmail(String email);
    Personal findById(Long id);
    void changeInfo(Personal personalUpdated);
}
