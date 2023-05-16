package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.exceptions.EmailAlreadyRegisteredException;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.repository.user.PersonalRepository;
import ua.com.alevel.service.user.PersonalService;

import java.util.Optional;

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
    public Personal findById(Long id) {
        return personalRepository.findById(id).get();
    }

    @Override
    public void changeInfo(Personal personalUpdated) {
        boolean existEmail = personalRepository.existsByEmail(personalUpdated.getEmail());
        if (existEmail) {
            Personal personalByEmail = personalRepository.findByEmail(personalUpdated.getEmail()).get();
            if (personalUpdated.getId().equals(personalByEmail.getId())) {
                personalRepository.save(personalUpdated);
            } else {
                throw new EmailAlreadyRegisteredException("Such email is already registered in system!");
            }
        } else {
            personalRepository.save(personalUpdated);
        }
    }
}
