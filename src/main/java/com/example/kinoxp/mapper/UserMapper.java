package com.example.kinoxp.mapper;

import com.example.kinoxp.dto.UserDTO;
import com.example.kinoxp.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toUserDTO(User user) {
        if (user == null) return null;
        return new UserDTO(
                user.getId(),
                user.getAddress(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getUserType(),
                user.getUserName(),
                null
        );
    }


    public User toUser(UserDTO dto) {
        if (dto == null) return null;
        User user = new User();
        user.setId(dto.getId());
        user.setAddress(dto.getAddress());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setUserType(dto.getUserType());
        user.setUserName(dto.getUserName());
        return user;

    }
}
