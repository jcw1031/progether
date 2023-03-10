package com.woopaca.progether.controller.dto;

import com.woopaca.progether.entity.PostStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostListResponseDto {

    private String postTitle;
    private String requiredSkills;
    private String postDate;
    private String writer;
    private PostStatus postStatus;

    @Builder

    public PostListResponseDto(String postTitle, String requiredSkills, String postDate,
                               String writer, PostStatus postStatus) {
        this.postTitle = postTitle;
        this.requiredSkills = requiredSkills;
        this.postDate = postDate;
        this.writer = writer;
        this.postStatus = postStatus;
    }
}
