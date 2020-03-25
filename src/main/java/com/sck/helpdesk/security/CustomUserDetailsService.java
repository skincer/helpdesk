package com.sck.helpdesk.security;

import com.sck.helpdesk.domain.UserEntity;
import com.sck.helpdesk.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsername(s);
        if(userEntity == null) throw new UsernameNotFoundException(s);

        return new CustomUserDetails(userEntity);
    }
}
