package com.example.scheduleproject.repository;

import com.example.scheduleproject.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // 특정 사용자가 작성한 일정 목록 조회
    List<Schedule> findAllByUserId(Long userId);

    // 제목으로 일정 검색 (부분 검색)
    List<Schedule> findByTitleContaining(String keyword);
}
