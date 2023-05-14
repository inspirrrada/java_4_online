package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.exceptions.EmailAlreadyRegisteredException;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.repository.user.PersonalRepository;
import ua.com.alevel.service.user.PersonalService;

@Service
public class PersonalServiceImpl implements PersonalService {

    private final PersonalRepository personalRepository;

    public PersonalServiceImpl(PersonalRepository personalRepository) {
        this.personalRepository = personalRepository;
    }

    @Override
    public Personal findByEmail(String email) {
        return personalRepository.findByEmail(email).get();
    }

    @Override
    public void changeInfo(Personal personal) {
        boolean existEmail = personalRepository.existsByEmail(personal.getEmail());
        if (existEmail) {
            Personal personalByEmail = personalRepository.findByEmail(personal.getEmail()).get();
            boolean isTheSameIdByEmail = personalByEmail.getId().equals(personal.getId());
            if (!isTheSameIdByEmail) {
                throw new EmailAlreadyRegisteredException("Such email is already registered in system!");
            } else {
                personal.setPassword(personalByEmail.getPassword());
                personal.setRoleType(personalByEmail.getRoleType());
                personalRepository.save(personal);
            }
        } else {
            Personal personalById = personalRepository.findById(personal.getId()).get();
            personal.setPassword(personalById.getPassword());
            personal.setRoleType(personalById.getRoleType());
            personalRepository.save(personal);
        }
    }
}
