package fr.vocaltech.tdd.domains;

import fr.vocaltech.tdd.domains.models.User;

public interface UserRepository {
    User findUserById(int i);
}
