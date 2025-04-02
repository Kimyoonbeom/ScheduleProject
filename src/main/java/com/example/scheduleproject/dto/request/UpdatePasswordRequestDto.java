package com.example.scheduleproject.dto.request;

import lombok.Getter;

// 비밀번호를 변경할 때 필요한 데이터를 전달하는 DTO
@Getter
public class UpdatePasswordRequestDto {

    private final String oldPassword;
    private final String newPassword;

    public UpdatePasswordRequestDto(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
