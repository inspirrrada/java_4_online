package ua.com.alevel.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.data.request.AuthData;
import ua.com.alevel.persistence.entity.cart.Cart;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.repository.CartRepository;
import ua.com.alevel.persistence.repository.user.PersonalRepository;
import ua.com.alevel.service.RegistrationService;
import ua.com.alevel.service.user.PersonalService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final PersonalService personalService;
//    private final PersonalRepository personalRepository;
    private final CartRepository cartRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public RegistrationServiceImpl(PersonalService personalService, PersonalRepository personalRepository, CartRepository cartRepository, BCryptPasswordEncoder passwordEncoder) {
        this.personalService = personalService;
//        this.personalRepository = personalRepository;
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
