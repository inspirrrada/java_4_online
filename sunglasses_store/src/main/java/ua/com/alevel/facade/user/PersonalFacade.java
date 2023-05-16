package ua.com.alevel.facade.user;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.data.response.PersonalAddressDto;
import ua.com.alevel.data.response.PersonalInfoDto;
import ua.com.alevel.data.response.PersonalPasswordDto;
import ua.com.alevel.persistence.entity.user.Personal;

public interface PersonalFacade {

    Personal findByEmail(String email);
    boolean changeInfo(PersonalInfoDto personalInfoDto,
                       PersonalPasswordDto personalPasswordDto,
                       PersonalAddressDto personalAddressDto, WebRequest webRequest);
}
