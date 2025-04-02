package com.example.scheduleproject.dto.request;


import lombok.Getter;

// 회원가입 시 필요한 데이터를 전달하기 위한 DTO
@Getter
public class SignUpRequestDto {

    private final String username;

    private final String email;

    private final String password;

    public SignUpRequestDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
