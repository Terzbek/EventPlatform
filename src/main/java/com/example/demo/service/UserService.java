package com.example.demo.service;


import com.example.demo.model.User;
import com.example.demo.model.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user){
        userRepository.save(user);
        System.out.println("User has been saved successfully!");
    }

    public User customSave(String firstname, String lastname, String email, String password){
        User user = new User();
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEmail(email);
        user.setPassword(password);

        return userRepository.save(user);
    }
}
