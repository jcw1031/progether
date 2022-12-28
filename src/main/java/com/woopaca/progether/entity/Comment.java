package com.woopaca.progether.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
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
