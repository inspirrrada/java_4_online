package ua.com.alevel.service.user.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.user.UserRepository;
import ua.com.alevel.service.user.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return (User) userRepository.findById(id).get();
    }
}
