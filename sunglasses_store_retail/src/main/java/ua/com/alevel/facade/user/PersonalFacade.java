package ua.com.alevel.facade.user;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.dto.user.PersonalAddressDto;
import ua.com.alevel.dto.user.PersonalInfoDto;
import ua.com.alevel.dto.user.PersonalPasswordDto;
import ua.com.alevel.persistence.entity.user.Personal;

public interface PersonalFacade {

    Personal findByEmail(String email);
    PersonalInfoDto findPersonalInfo(Personal personal);
    PersonalPasswordDto findPersonalPassword(Personal personal);
    PersonalAddressDto findPersonalAddress(Personal personal);
    boolean changeInfo(PersonalInfoDto personalInfoDto,
                       PersonalPasswordDto personalPasswordDto,
                       PersonalAddressDto personalAddressDto, WebRequest webRequest);
}
