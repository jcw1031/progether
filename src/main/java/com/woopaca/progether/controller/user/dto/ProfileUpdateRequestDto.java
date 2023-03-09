package com.woopaca.progether.controller.user.dto;

import com.woopaca.progether.entity.Part;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProfileUpdateRequestDto {

    private String subject;
    private String introduction;
    private String website;
    private Part part;
}
