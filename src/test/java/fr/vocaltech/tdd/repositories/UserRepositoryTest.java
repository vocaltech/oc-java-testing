package fr.vocaltech.tdd.repositories;

import fr.vocaltech.tdd.domains.models.User;
import fr.vocaltech.tdd.domains.repositories.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {
    @Mock
    UserRepository userRepository;

    @ParameterizedTest(name = "Find user by id: {0}")
    @ValueSource(ints = { 1, 2 })
    void givenUserId_whenFindUserById_thenReturnUser(int id) {
        // GIVEN
        switch (id) {
            case 1 -> when(userRepository.findUserById(1)).thenReturn(new User("Mellin Dah"));
            case 2 -> when(userRepository.findUserById(2)).thenReturn(new User("John Doe"));
        }

        // WHEN
        User foudUser = userRepository.findUserById(id);
        verify(userRepository, times(1)).findUserById(id);

        // THEN
        switch(id) {
            case 1 -> assertThat(foudUser.fullName()).isEqualTo("Mellin Dah");
            case 2 -> assertThat(foudUser.fullName()).isEqualTo("John Doe");
        }
    }
}
