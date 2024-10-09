package com.example.kinoxp.dto;

import com.example.kinoxp.enums.UserType;

public class UserDTO {
    private int id;
    private String address;
    private String email;
    private String phoneNumber;
    private UserType userType;
    private String userName;
    private String password;

    public UserDTO() {

    }

    public UserDTO(int id, String address, String email, String phoneNumber, UserType userType, String userName, String password) {
        this.id = id;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        this.userName = userName;
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
