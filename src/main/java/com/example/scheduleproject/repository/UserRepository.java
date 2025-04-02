package com.example.scheduleproject.repository;

import com.example.scheduleproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 이메일로 사용자 검색 (로그인 시 사용)
    Optional<User> findByEmail(String email);

    // 사용자 이름으로 검색
    Optional<User> findByUsername(String username);

    // 이메일 중복 여부 확인
    boolean existsByEmail(String email);

    // 사용자명 중복 여부 확인
    boolean existsByUsername(String username);

}
