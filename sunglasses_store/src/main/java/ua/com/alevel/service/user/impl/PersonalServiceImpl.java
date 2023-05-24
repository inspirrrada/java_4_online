package ua.com.alevel.service.user.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.alevel.exceptions.EmailAlreadyRegisteredException;
import ua.com.alevel.persistence.entity.cart.Cart;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.repository.cart.CartRepository;
import ua.com.alevel.persistence.repository.user.PersonalRepository;
import ua.com.alevel.service.user.PersonalService;

@Service
public class PersonalServiceImpl implements PersonalService {

    private final PersonalRepository personalRepository;
    private final CartRepository cartRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public PersonalServiceImpl(PersonalRepository personalRepository, CartRepository cartRepository, BCryptPasswordEncoder passwordEncoder) {
        this.personalRepository = personalRepository;
        this.cartRepository = cartRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Personal findByEmail(String email) {
        return personalRepository.findByEmail(email).get();
    }

    @Override
    public boolean existsByEmail(String email) {
        return personalRepository.existsByEmail(email);
    }

    @Override
    public Personal findById(Long id) {
        return personalRepository.findById(id).get();
    }

    @Override
    public void createAccount(Personal personal) {
        Cart cart = new Cart();
        cart.setUser(personal);
        personalRepository.save(personal);
        cartRepository.save(cart);
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
