package com.woopaca.progether.controller.user.dto;

import com.woopaca.progether.entity.Part;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileUpdateRequestDto {

    private String subject;
    private String introduction;
    private String website;
    private Part part;
}
