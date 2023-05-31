package ua.com.alevel.service.user.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.dto.user.AuthData;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.repository.cart.CartRepository;
import ua.com.alevel.persistence.repository.user.PersonalRepository;
import ua.com.alevel.service.user.RegistrationService;
import ua.com.alevel.service.user.PersonalService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final PersonalService personalService;
    private final CartRepository cartRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public RegistrationServiceImpl(PersonalService personalService, CartRepository cartRepository, BCryptPasswordEncoder passwordEncoder) {
        this.personalService = personalService;
        this.cartRepository = cartRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void registration(AuthData authData) {
        if (personalService.existsByEmail(authData.getEmail())) {
            throw new RuntimeException("User with such email already exist!");
        }
        Personal personal = new Personal();
        personal.setEmail(authData.getEmail());
        personal.setPassword(passwordEncoder.encode(authData.getPassword()));
        personalService.createAccount(personal);
    }
}
