package com.fencingstats.fenzapp;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fencingstats.fenzapp.UserRepository;
import com.fencingstats.fenzapp.User;

@Service
public class UserService {

    private  UserRepository userRepository;
    private  PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerNewUserAccount(UserRegistrationDto registrationDto) throws UserAlreadyExistException {
        if (emailExist(registrationDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + registrationDto.getEmail());
        }

        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setRole("USER");

        return userRepository.save(user);
    }

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

}