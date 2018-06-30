package com.travel.core.service;

import com.travel.core.domain.Gas;
import com.travel.core.domain.User;
import com.travel.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Transactional(readOnly = true)
    public User findByEmailOrUsername(String key){
        Optional<User> optional= userRepository.findByEmailOrUsername(key);
        User obj = optional.get();
        return obj ;
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User save(User newUser){
        newUser.setAccountExpired(false);
        newUser.setEnabled(true);
        newUser.setCredentialExpired(false);
        newUser.setAccountLocked(false);
        String encodedPassword = encoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);
        userRepository.save(newUser);

        return newUser;
    }
}
