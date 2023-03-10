package com.woopaca.progether.controller.dto;

import com.woopaca.progether.entity.PostStatus;
import com.woopaca.progether.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostListResponseDto {

    private Long postId;
    private String postTitle;
    private String requiredSkills;
    private String postDate;
    private User writer;
    private PostStatus postStatus;

    @Builder

    public PostListResponseDto(Long postId, String postTitle, String requiredSkills, String postDate,
                               User writer, PostStatus postStatus) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.requiredSkills = requiredSkills;
        this.postDate = postDate;
        this.writer = writer;
        this.postStatus = postStatus;
    }
}
