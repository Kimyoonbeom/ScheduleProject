package com.example.scheduleproject.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

// 회원가입 시 필요한 데이터를 전달하기 위한 DTO
// @Pattern을 사용해서 회원 가입 Email 데이터 검증 등
@Getter
public class SignUpRequestDto {

    @NotBlank(message = "유저명을 입력하세요.")
    @Size(max = 10, message = "유저명은 최대 10글자까지 가능합니다.")
    private final String username;

    @NotBlank(message = "이메일을 입력하세요.")
    @Email(message = "유효한 이메일 형식이어야 합니다.")
    private final String email;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하여야 합니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\\\d)[A-Za-z\\\\d@$!%*?&]+$")
    // regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$" -> 8~20자리 숫자, 대소문자, 특수문자 포함.
    private final String password;

    public SignUpRequestDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
