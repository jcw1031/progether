package com.woopaca.progether.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    private Long id;
    private String email;
    private String password;
    private String subject;
    private String name;
    private String skills;
    private String introduce;
    private String website;
    private int postsNumber;

    @Builder
    public User(String email, String password, String subject, String name, String skills,
                String introduce, String website, int postsNumber) {
        this.email = email;
        this.password = password;
        this.subject = subject;
        this.name = name;
        this.skills = skills;
        this.introduce = introduce;
        this.website = website;
        this.postsNumber = postsNumber;
    }
}
