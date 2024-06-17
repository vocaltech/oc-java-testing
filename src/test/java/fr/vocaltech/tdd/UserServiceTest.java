package fr.vocaltech.tdd;

import fr.vocaltech.tdd.domains.UserRepository;
import fr.vocaltech.tdd.domains.models.User;
import fr.vocaltech.tdd.services.UserService;
import fr.vocaltech.tdd.services.UserServiceImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @ParameterizedTest(name = "Handling userid {0}")
    @ValueSource(ints = { 1, 2 })
    void givenUserId_whenFindUserById_thenReturnUser(int id) {
        // given (arrange)
        UserRepository mockUserRepository = mock();
        switch (id) {
            case 1 -> when(mockUserRepository.findUserById(1)).thenReturn(new User("Pat Raz"));
            case 2 -> when(mockUserRepository.findUserById(2)).thenReturn(new User("John Doe"));
        }

        UserService userService = new UserServiceImpl(mockUserRepository);

        // when (act)
        User foundUser = userService.findUserById(id);
        verify(mockUserRepository, times(1)).findUserById(id);

        // then (assert)
        switch(id) {
            case 1 -> assertThat(foundUser.getFullName()).isEqualTo("Pat Raz");
            case 2 -> assertThat(foundUser.getFullName()).isEqualTo("John Doe");
        }
    }
}
