package com.woopaca.progether.controller.user.dto;

import com.woopaca.progether.entity.Part;
import com.woopaca.progether.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfileUpdateRequestDto {

    private String subject;
    private String introduction;
    private String website;
    private Part part;

    @Builder
    public ProfileUpdateRequestDto(String subject, String introduction, String website, Part part) {
        this.subject = subject;
        this.introduction = introduction;
        this.website = website;
        this.part = part;
    }

    public static ProfileUpdateRequestDto from(final User user) {
        return ProfileUpdateRequestDto.builder()
                .subject(user.getSubject())
                .introduction(user.getIntroduction())
                .website(user.getWebsite())
                .part(user.getPart())
                .build();
    }
}
