package ua.com.alevel.facade.user.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.data.response.PersonalInfoDto;
import ua.com.alevel.facade.user.PersonalFacade;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.service.user.PersonalService;

@Service
public class PersonalFacadeImpl implements PersonalFacade {

    private final PersonalService personalService;

    public PersonalFacadeImpl(PersonalService personalService) {
        this.personalService = personalService;
    }

    @Override
    public Personal findByEmail(String email) {
        return personalService.findByEmail(email);
    }

    @Override
    public boolean changeInfo(PersonalInfoDto dto) {
        Personal personal = new Personal();
        personal.setId(dto.getId());
        personal.setFirstName(dto.getFirstName());
        personal.setLastName(dto.getLastName());
        personal.setFatherName(dto.getFatherName());
        personal.setEmail(dto.getEmail());
        personal.setPhoneNumber(dto.getPhoneNumber());
        personal.setBirthDay(dto.getBirthDay());
        try {
            personalService.changeInfo(personal);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
