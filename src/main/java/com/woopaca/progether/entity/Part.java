package com.woopaca.progether.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Part {

    BACK_END("백엔드"),
    FRONT_END("프론트엔드"),
    DESIGN("디자인"),
    PLAN("기획");

    private final String partName;
}
