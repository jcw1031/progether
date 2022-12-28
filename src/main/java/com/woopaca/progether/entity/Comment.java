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
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "내용을 입력해 주세요.")
    @Size(max = 500)
    private String content;
    @NotNull
    private String date;
    @ManyToOne
    private Member writer;
    @ManyToOne
    private Post post;

    @Builder
    public Comment(Long id, String content, String date, Member writer, Post post) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.writer = writer;
        this.post = post;
    }
}
