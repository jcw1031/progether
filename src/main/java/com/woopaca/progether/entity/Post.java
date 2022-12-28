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
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String requiredSkills;
    private String date;
    private Boolean complete;
    @ManyToOne
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