package com.state.street.backend.user;

import com.state.street.backend.exceptions.user.UserNotFoundException;
import com.state.street.backend.model.dto.UserDto;
import com.state.street.backend.model.entity.User;
import com.state.street.backend.repositories.UserRepository;
import com.state.street.backend.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getUserByEmailShouldReturnAllUserWithGivenEmail() {
        User mockUser1 = User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .drivingLicenseId("DL123456")
                .emailAddress("john@example.com")
                .phoneNumber("111111111")
                .build();

        User mockUser2 = User.builder()
                .id(2L)
                .firstName("Bob")
                .lastName("Smith")
                .drivingLicenseId("DL654321")
                .emailAddress("john@example.com")
                .phoneNumber("222222222")
                .build();

        List<User> users = List.of(mockUser1, mockUser2);

        when(userRepository.findAllByEmailAddress("john@example.com"))
                .thenReturn(users);

        List<User> result = userRepository.findAllByEmailAddress("john@example.com");

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(2, result.size()),
                () -> assertEquals(mockUser1, result.getFirst()),
                () -> assertEquals(mockUser2, result.get(1))
        );

        verify(userRepository).findAllByEmailAddress("john@example.com");
    }

    @Test
    void getUserByEmailShouldReturnEmptyListWhenNoUsersFound() {
        when(userRepository.findAllByEmailAddress("john@example.com"))
                .thenReturn(List.of());

        List<User> result = userRepository.findAllByEmailAddress("john@example.com");

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(0, result.size())
        );
    }

    @Test
    void getUserByIdShouldReturnUserWhenFound() throws UserNotFoundException {
        User mockUser = User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .drivingLicenseId("DL123456")
                .emailAddress("john@example.com")
                .phoneNumber("111111111")
                .build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        User result = userService.getUserById(1L);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(mockUser.getId(), result.getId()),
                () -> assertEquals(mockUser.getFirstName(), result.getFirstName()),
                () -> assertEquals(mockUser.getLastName(), result.getLastName()),
                () -> assertEquals(mockUser.getDrivingLicenseId(), result.getDrivingLicenseId()),
                () -> assertEquals(mockUser.getEmailAddress(), result.getEmailAddress()),
                () -> assertEquals(mockUser.getPhoneNumber(), result.getPhoneNumber())
        );
    }

    @Test
    void getUserByIdShouldThrowUserNotFoundExceptionWhenUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(1L));
    }

    @Test
    void createNewUserShouldSaveMappedUserAndReturnSavedUser() {
        UserDto newUser = UserDto.builder()
                .firstName("John")
                .lastName("Doe")
                .drivingLicenseId("DL123456")
                .emailAddress("john@example.com")
                .phoneNumber("123456789")
                .build();

        User savedUser = User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .drivingLicenseId("DL123456")
                .emailAddress("john@example.com")
                .phoneNumber("123456789")
                .build();

        when(userRepository.save(userCaptor.capture())).thenReturn(savedUser);

        User result = userService.createNewUser(newUser);

        verify(userRepository).save(savedUser);

        User capturedUser = userCaptor.getValue();

        assertAll(
                () -> assertNotNull(result),

                () -> assertNull(capturedUser.getId()),
                () -> assertEquals(newUser.firstName(), capturedUser.getFirstName()),
                () -> assertEquals(newUser.lastName(), capturedUser.getLastName()),
                () -> assertEquals(newUser.drivingLicenseId(), capturedUser.getDrivingLicenseId()),
                () -> assertEquals(newUser.emailAddress(), capturedUser.getEmailAddress()),
                () -> assertEquals(newUser.phoneNumber(), capturedUser.getPhoneNumber()),

                () -> assertEquals(savedUser.getId(), result.getId()),
                () -> assertEquals(savedUser.getFirstName(), result.getFirstName()),
                () -> assertEquals(savedUser.getLastName(), result.getLastName()),
                () -> assertEquals(savedUser.getDrivingLicenseId(), result.getDrivingLicenseId()),
                () -> assertEquals(savedUser.getEmailAddress(), result.getEmailAddress()),
                () -> assertEquals(savedUser.getPhoneNumber(), result.getPhoneNumber())
        );
    }

    @Test
    void checkIfUserAlreadyExistsShouldReturnUserIdWhenMatchingUserExists() {
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .drivingLicenseId("DL123456")
                .emailAddress("john@example.com")
                .phoneNumber("123456789")
                .build();

        User userFromDb = User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .drivingLicenseId("DL123456")
                .emailAddress("john@example.com")
                .phoneNumber("123456789")
                .build();

        when(userRepository.findAllByEmailAddress("john@example.com"))
                .thenReturn(List.of(userFromDb));

        Optional<Long> result = userService.checkIfUserAlreadyExists(user);

        assertAll(
                () -> assertTrue(result.isPresent()),
                () -> assertEquals(1L, result.get())
        );

        verify(userRepository).findAllByEmailAddress("john@example.com");
    }

    @Test
    void checkIfUserAlreadyExistsShouldReturnEmptyOptionalWhenNoMatchingUserExists() {
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .drivingLicenseId("DL123456")
                .emailAddress("john@example.com")
                .phoneNumber("123456789")
                .build();

        User userFromDb = User.builder()
                .id(1L)
                .firstName("Bob")
                .lastName("Doe")
                .drivingLicenseId("DL999999")
                .emailAddress("john@example.com")
                .phoneNumber("987654321")
                .build();

        when(userRepository.findAllByEmailAddress("john@example.com"))
                .thenReturn(List.of(userFromDb));

        Optional<Long> result = userService.checkIfUserAlreadyExists(user);

        assertTrue(result.isEmpty());

        verify(userRepository).findAllByEmailAddress("john@example.com");
    }
}
