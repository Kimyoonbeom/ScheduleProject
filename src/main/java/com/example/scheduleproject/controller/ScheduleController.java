package com.example.scheduleproject.controller;

import com.example.scheduleproject.dto.request.CreateScheduleRequestDto;
import com.example.scheduleproject.dto.request.UpdateScheduleRequestDto;
import com.example.scheduleproject.dto.response.ScheduleResponseDto;
import com.example.scheduleproject.entity.Schedule;
import com.example.scheduleproject.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성(createSchedule)
    @PostMapping("/schedules")
    public ResponseEntity<ScheduleResponseDto> createSchedule
    (@RequestBody CreateScheduleRequestDto requestDto){
        Schedule schedule = scheduleService.createSchedule(
                requestDto.getTitle(),
                requestDto.getContents(),
                requestDto.getUserId());
        return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.CREATED);
    }

    // 전체 일정 조회(findAllSchedules)
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules() {
        List<Schedule> schedules = scheduleService.findAllSchedules();
        List<ScheduleResponseDto> responseDtos = schedules.stream()
                .map(ScheduleResponseDto::new)
                .toList();
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    // 특정 일정 조회(findSchedulesById)
    @GetMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long id){
        Schedule schedule = scheduleService.findSchledulesById(id);
        return new ResponseEntity<>(new ScheduleResponseDto(schedule),HttpStatus.OK);
    }

    // 일정 업데이트, 수정(updateSchedule)
    @PutMapping("/schedules/{id}")
    public ResponseEntity<Void> updateSchedule(@PathVariable Long id,
                                               @RequestBody UpdateScheduleRequestDto requestDto){
        scheduleService.updateSchedule(id, requestDto.getTitle(), requestDto.getContents());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    // 일정 삭제(deleteSchedule)
    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id){
        scheduleService.deleteSchedule(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
