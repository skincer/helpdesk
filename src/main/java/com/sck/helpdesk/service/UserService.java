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

    public UserEntity editUser(final Long id, final String username, final UserEntity.UserType userType) throws Exception {

        UserEntity userEntity = userRepository.getOne(id);

        if(!userEntity.getUsername().equalsIgnoreCase(username)) {
            if(userRepository.findByUsername(username) != null) throw new Exception("Username already exists");
        }

        userEntity.setUsername(username);
        userEntity.setType(userType);

        return userRepository.save(userEntity);
    }

    public UserEntity createUser(final String username, final String password, final UserEntity.UserType userType) throws Exception {

        if(userRepository.findByUsername(username) != null) throw new Exception("Username already exists");

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
