package com.sck.helpdesk.service;

import com.sck.helpdesk.domain.UserEntity;
import com.sck.helpdesk.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity createUser(final String username, final String password, final UserEntity.UserType userType) {

        // Mayhap throw exception here instead
        if(userRepository.findByUsername(username) != null) return null;

        UserEntity user = new UserEntity(
                username, passwordEncoder.encode(password), userType
        );

        return userRepository.save(user);
    }

    public UserEntity retrieveAvailableAgent() {

        List<UserEntity> agents = userRepository.findFirstByTypeOrderByTicketsAssigned(UserEntity.UserType.AGENT);

        return agents.get(0);

    }
}
