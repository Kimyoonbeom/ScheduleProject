package com.example.scheduleproject.dto.response;

import com.example.scheduleproject.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private final Long id;

    private final String username;

    private final String email;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
