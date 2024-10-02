package com.example.kinoxp.service;

import com.example.kinoxp.model.User;
import com.example.kinoxp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null); //Return null or handle exception
    }
    public User updateUser(int id, User userDetails) {
        User user = getUserById(id);
        if (user != null) {
            user.setUserType(userDetails.getUserType());
            user.setName(userDetails.getName());
            user.setPhoneNumber(userDetails.getPhoneNumber());
            user.setEmail(userDetails.getEmail());
            user.setAddress(userDetails.getAddress());
            return userRepository.save(user);
        }
        return null;
    }
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
