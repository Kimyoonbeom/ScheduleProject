package com.example.scheduleproject.dto.request;

import lombok.Getter;

@Getter
// 일정 수정할 때 필요한 데이터 전달
public class UpdateScheduleRequestDto {
    private final String title;
    private final String contents;

    public UpdateScheduleRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
