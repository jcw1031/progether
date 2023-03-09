package com.woopaca.progether.controller.user.dto;

import com.woopaca.progether.entity.Part;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfileResponseDto {

    private String email;
    private String name;
    private String subject;
    private String introduction;
    private String website;
    private int postNumber;
    private Part part;

    @Builder
    public UserProfileResponseDto(String email, String name, String subject, String introduction,
                                  String website, int postNumber, Part part) {
        this.email = email;
        this.name = name;
        this.subject = subject;
        this.introduction = introduction;
        this.website = website;
        this.postNumber = postNumber;
        this.part = part;
    }
}
