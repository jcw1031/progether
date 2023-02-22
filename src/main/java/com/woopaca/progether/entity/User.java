package com.woopaca.progether.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String subject;
    @Column(nullable = false)
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
