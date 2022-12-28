package com.woopaca.progether.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "제목을 입력해 주세요.")
    @Size(min = 2, max = 50)
    private String title;
    @NotBlank(message = "내용을 입력해 주세요.")
    @Size(min = 10)
    private String content;
    private String requiredSkills;
    @NotNull
    private String date;
    @NotNull
    private Boolean complete;
    @ManyToOne
    @NotNull
    private Member writer;

    @Builder
    public Post(Long id, String title, String content, String requiredSkills, String date,
                Boolean complete, Member writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.requiredSkills = requiredSkills;
        this.date = date;
        this.complete = complete;
        this.writer = writer;
    }
}