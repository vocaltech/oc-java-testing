package fr.vocaltech.tdd.services;

import fr.vocaltech.tdd.domains.models.User;
import fr.vocaltech.tdd.domains.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;


    @ParameterizedTest(name = "Find user by id: {0}")
    @ValueSource(ints = { 1, 2 })
    void givenUserId_whenFindUserById_thenReturnUser(int id) {
        // GIVEN (arrange)
        switch (id) {
            case 1 -> when(userRepository.findUserById(1)).thenReturn(new User("Mellin Dah"));
            case 2 -> when(userRepository.findUserById(2)).thenReturn(new User("John Doe"));
        }

        UserService userService = new UserServiceImpl(userRepository);

        // WHEN (act)
        User foundUser = userService.findUserById(id);
        verify(userRepository, times(1)).findUserById(id);

        // THEN (assert)
        switch(id) {
            case 1 -> assertThat(foundUser.fullName()).isEqualTo("Mellin Dah");
            case 2 -> assertThat(foundUser.fullName()).isEqualTo("John Doe");
        }
    }

    @Test
    void dummyTest() {
        // given
        List<String> mockedList = mock();
        lenient().when(mockedList.get(0)).thenReturn("elt0");
        when(mockedList.get(1)).thenReturn("elt1");

        // when
        String s1 = mockedList.get(1);
        verify(mockedList, times(1)).get(anyInt());

        // then
        assertThat(s1).isEqualTo("elt1");

    }
}
