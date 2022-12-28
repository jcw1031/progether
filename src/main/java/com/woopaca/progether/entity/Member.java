package com.woopaca.progether.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    public Member(Long id, String email, String password, String subject, String name, String skills,
                  String introduce, String website, int postsNumber) {
        this.id = id;
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
