package com.example.scheduleproject.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

// 비밀번호를 변경할 때 필요한 데이터를 전달하는 DTO
@Getter
public class UpdatePasswordRequestDto {

    @NotBlank(message = "기존 비밀번호를 입력하세요.")
    private final String oldPassword;

    @NotBlank(message = "새 비밀번호를 입력하세요.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하여야 합니다.")
    private final String newPassword;

    public UpdatePasswordRequestDto(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
