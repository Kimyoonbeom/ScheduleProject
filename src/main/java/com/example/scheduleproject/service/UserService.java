package com.example.scheduleproject.service;

import com.example.scheduleproject.config.PasswordEncoder;
import com.example.scheduleproject.entity.User;
import com.example.scheduleproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

// 사용자 생성(SignUp), 조회(findById), 비번 업뎃(updatePassword), 삭제(deleteUser).
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화 하기.


    @Transactional
    public User signUp(String username, String email, String password){
        if (userRepository.existsByUsername(username)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is already exists");
        }
        if (userRepository.existsByEmail(email)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already exists");
        }
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(username, email, encodedPassword);
        return userRepository.save(user);
    }

    @Transactional
    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found"));
    }

    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword){
        User user = findById(id);
        if (!passwordEncoder.matches(oldPassword, user.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Old password is not correct");
        }
        user.updatePassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
    @Transactional
    public void deleteUser(Long id){
        User user = findById(id);
        userRepository.delete(user);
    }
}
