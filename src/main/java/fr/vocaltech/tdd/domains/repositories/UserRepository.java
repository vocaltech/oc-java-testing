package fr.vocaltech.tdd.domains.repositories;

import fr.vocaltech.tdd.domains.models.User;

public interface UserRepository {
    User findUserById(int id);
}
