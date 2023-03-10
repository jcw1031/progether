package com.woopaca.progether.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostStatus {

    PROGRESS("진행(모집) 중"),
    COMPLETE("완료");

    private final String status;
}
