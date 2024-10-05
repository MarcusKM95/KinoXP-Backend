package com.example.kinoxp.model;

import com.example.kinoxp.enums.UserType;
import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int Id;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(nullable = false, name = "username")
    private String name;

    @Column(nullable = false)
    private int phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    private String password; // Nullable for guests

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
