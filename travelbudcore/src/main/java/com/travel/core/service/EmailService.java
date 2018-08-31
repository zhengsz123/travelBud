package com.travel.core.service;


import com.travel.core.domain.User;
import com.travel.core.email.RegistrationEmail;
import com.travel.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmailService {
    @Autowired
    private RegistrationEmail registrationEmail;
    @Autowired
    private UserRepository userRepository;
    @Transactional
    public void sendConfirmEmail(Long id){
        Optional<User> optional = userRepository.findById(id);
        User user = optional.get();
        registrationEmail.confirmEmail(user,null);
    }
}
