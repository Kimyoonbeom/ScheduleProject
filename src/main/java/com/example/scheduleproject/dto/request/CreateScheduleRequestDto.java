package com.example.scheduleproject.dto.request;

import lombok.Getter;

@Getter
// 일정 생성 시 필요한 데이터 전달.
public class CreateScheduleRequestDto {
    private final String title;
    private final String contents;
    private final Long userId;

    public CreateScheduleRequestDto(String title, String contents, Long userId) {
        this.title = title;
        this.contents = contents;
        this.userId = userId;
    }
}
