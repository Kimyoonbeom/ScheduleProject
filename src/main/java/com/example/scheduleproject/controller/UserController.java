package com.example.scheduleproject.controller;

import com.example.scheduleproject.dto.request.LoginRequestDto;
import com.example.scheduleproject.dto.request.SignUpRequestDto;
import com.example.scheduleproject.dto.request.UpdatePasswordRequestDto;
import com.example.scheduleproject.dto.response.UserResponseDto;
import com.example.scheduleproject.entity.User;
import com.example.scheduleproject.service.ScheduleService;
import com.example.scheduleproject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ScheduleService scheduleService;

    // 회원가입
    @PostMapping("users/signup")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody SignUpRequestDto requestDto) {
        User user = userService.signUp(requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword());
        return new ResponseEntity<>(new UserResponseDto(user), HttpStatus.CREATED);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequestDto requestDto, HttpServletRequest request) {
        User user = userService.authenticate(requestDto.getEmail(), requestDto.getPassword());

        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getId());
        return ResponseEntity.ok().build();
    }

    // 사용자 조회
    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id){
        User user = userService.findById(id);
        return new ResponseEntity<>(new UserResponseDto(user), HttpStatus.OK);
    }

    // 비밀번호 변경
    @PatchMapping("/users/{id}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody UpdatePasswordRequestDto requestDto){
        scheduleService.updateSchedule(id, requestDto.getOldPassword(), requestDto.getNewPassword());
        return new ResponseEntity<>(HttpStatus.OK);

    }

    // 사용자 삭제
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
