package com.woopaca.progether.controller.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfileResponseDto {

    private String email;
    private String name;
    private String part;
    private String subject;
    private String introduction;
    private String website;
    private int postNumber;

    @Builder
    public UserProfileResponseDto(String email, String name, String part, String subject, String introduction,
                                  String website, int postNumber) {
        this.email = email;
        this.name = name;
        this.part = part;
        this.subject = subject;
        this.introduction = introduction;
        this.website = website;
        this.postNumber = postNumber;
    }
}
