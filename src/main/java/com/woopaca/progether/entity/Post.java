package com.woopaca.progether.entity;

import com.woopaca.progether.controller.dto.PostListResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    private String postTitle;
    private String postContent;
    private String requiredSkills;
    private String postDate;
    @Enumerated(value = EnumType.STRING)
    private PostStatus postStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User writer;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Post(String postTitle, String postContent, String requiredSkills, String postDate, PostStatus postStatus) {
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.requiredSkills = requiredSkills;
        this.postDate = postDate;
        this.postStatus = postStatus;
    }

    public void writePost(User user) {
        writer = user;
        user.getPosts().add(this);
    }

    public PostListResponseDto toListDto() {
        return PostListResponseDto.builder()
                .postId(id)
                .postTitle(postTitle)
                .requiredSkills(requiredSkills)
                .postDate(postDate)
                .writer(writer)
                .postStatus(postStatus)
                .build();
    }
}
