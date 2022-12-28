package com.woopaca.progether.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 8, max = 30)
    private String email;
    @NotNull
    @Size(min = 8, max = 20)
    private String password;
    private String subject;
    @NotNull
    @Size(min = 1, max = 20)
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
