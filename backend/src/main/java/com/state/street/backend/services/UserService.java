package com.state.street.backend.services;

import com.state.street.backend.mappers.UserMapper;
import com.state.street.backend.model.dto.UserDto;
import com.state.street.backend.model.entity.User;
import com.state.street.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> getUserByEmail(String emailAddress) {
        return this.userRepository.findByEmailAddress(emailAddress);
    }

    public User createNewUser(UserDto newUser) {
        return this.userRepository.save(UserMapper.toEntity(newUser));
    }

    public boolean checkIfUserAlreadyExists(User user) {
        Optional<User> userFromDb = this.getUserByEmail(user.getEmailAddress());
        return userFromDb.map(value -> value.equals(user)).orElse(false);
    }
}
