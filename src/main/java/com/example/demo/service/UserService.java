package com.example.demo.service;


import com.example.demo.model.User;
import com.example.demo.model.UserDto;
import com.example.demo.model.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalStateException("Email уже зарегистрирован");
        }

        if (userRepository.existsByTaxCode(userDto.getTaxCode())) {
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

    // 🔥 ВАЖНО: логинимся по taxCode
    @Override
    public UserDetails loadUserByUsername(String taxCode)
            throws UsernameNotFoundException {

        User user = userRepository.findByTaxCode(taxCode)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getTaxCode())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}
