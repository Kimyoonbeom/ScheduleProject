package com.example.scheduleproject.service;

import com.example.scheduleproject.entity.Schedule;
import com.example.scheduleproject.entity.User;
import com.example.scheduleproject.repository.ScheduleRepository;
import com.example.scheduleproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

// 일정 생성(createSchedule), 조회(findAllSchedules, findSchedulesById),
// 업데이트(updateSchedule), 삭제(deleteSchedule)
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public Schedule createSchedule(String title, String contents, Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found"));

        Schedule schedule = new Schedule(title, contents, user);
        return scheduleRepository.save(schedule);
    }

    @Transactional(readOnly = true)
    public List<Schedule> findAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Schedule findSchledulesById(Long id){
        return scheduleRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule is not found"));
    }

    @Transactional
    public void updateSchedule(Long id, String title, String contents){
        Schedule schedule = findSchledulesById(id);
        schedule.update(title, contents);
        scheduleRepository.save(schedule);
    }

    @Transactional
    public void deleteSchedule(Long id){
        Schedule schedule = findSchledulesById(id);
        scheduleRepository.delete(schedule);
    }
}
