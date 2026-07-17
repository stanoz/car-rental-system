package com.state.street.backend.services;

import com.state.street.backend.exceptions.user.UserNotFoundException;
import com.state.street.backend.mappers.UserMapper;
import com.state.street.backend.model.dto.UserDto;
import com.state.street.backend.model.entity.User;
import com.state.street.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getUserByEmail(String emailAddress) {
        return this.userRepository.findAllByEmailAddress(emailAddress);
    }

    public User getUserById(Long id) throws UserNotFoundException {
        return this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User createNewUser(UserDto newUser) {
        return this.userRepository.save(UserMapper.toEntity(newUser));
    }

    public Optional<Long> checkIfUserAlreadyExists(User user) {
        return getUserByEmail(user.getEmailAddress()).stream()
                .filter(user::equals)
                .map(User::getId)
                .findFirst();
    }
}
