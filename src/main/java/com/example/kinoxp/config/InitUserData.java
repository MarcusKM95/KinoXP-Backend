package com.example.kinoxp.config;

import com.example.kinoxp.model.User;
import com.example.kinoxp.model.UserType;
import com.example.kinoxp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitUserData implements CommandLineRunner {

    private final UserRepository userRepository;

    public InitUserData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User[] users = {
                new User(null, "address123", "user@gmail.com", "12344556", UserType.MOVIEMANAGER, "Ali", "password123"),
                new User(null, "Address456", "moviemanager@gmail.com", "2345567", UserType.RESERVATIONSMANAGER, "M", "password456"),
                new User(null, "address789", "customer@gmail.com", "987654443", UserType.CUSTOMER, "K", "password789")
        };

        try {
            for (User user : users) {
                System.out.println("Trying to save user: " + user.getUserName() +
                        " with userType: " + user.getUserType() +
                        " and email: " + user.getEmail());

                List<User> existingUser = userRepository.findByEmail(user.getEmail());

                if (existingUser.isEmpty()) {
                    userRepository.save(user);
                    System.out.println("Saved new user: " + user.getUserName());
                } else {
                    System.out.println("User with email: " + user.getEmail() + " already exists");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error creating user data: " + e.getMessage());
        }
    }
}
