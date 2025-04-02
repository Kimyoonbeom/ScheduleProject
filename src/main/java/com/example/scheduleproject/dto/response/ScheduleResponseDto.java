package com.example.scheduleproject.dto.response;

import com.example.scheduleproject.entity.Schedule;
import lombok.Getter;

// 일정 정보를 클라이언트에 반환
@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final String title;
    private final String contents;
    private final String username;

//    public ScheduleResponseDto(Long id, String title, String contents, String username) {
//        this.id = id;
//        this.title = title;
//        this.contents = contents;
//        this.username = username;
//    }
    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.username = schedule.getUser().getUsername(); // 작성자 이름
    }

}
