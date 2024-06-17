package fr.vocaltech.tdd.services;

import fr.vocaltech.tdd.domains.models.User;

public interface UserService {
    User findUserById(int i);
}
