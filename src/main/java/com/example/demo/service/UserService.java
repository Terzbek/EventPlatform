package com.example.demo.service;


import com.example.demo.model.User;
import com.example.demo.model.UserDto;
import com.example.demo.model.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())){
            throw new IllegalStateException("Email уже зарегистрирован");
        }

        if (userRepository.existsByTaxCode(userDto.getTaxCode())){
            throw new IllegalStateException("ИИН уже зарегистрирован");
        }

        User user = new User();
        user.setTaxCode(userDto.getTaxCode());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }
}
