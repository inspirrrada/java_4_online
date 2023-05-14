package ua.com.alevel.facade.user;

import ua.com.alevel.data.response.PersonalInfoDto;
import ua.com.alevel.persistence.entity.user.Personal;

public interface PersonalFacade {

    Personal findByEmail(String email);
    boolean changeInfo(PersonalInfoDto personalInfoDto);
}
