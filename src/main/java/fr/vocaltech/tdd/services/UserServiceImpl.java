package fr.vocaltech.tdd.services;

import fr.vocaltech.tdd.domains.repositories.UserRepository;
import fr.vocaltech.tdd.domains.models.User;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User findUserById(int i) {
        return userRepository.findUserById(i);
    }
}
