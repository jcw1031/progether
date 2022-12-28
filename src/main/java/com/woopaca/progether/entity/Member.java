package com.woopaca.progether.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "이메일을 입력해 주세요.")
    @Email(message = "이메일을 올바르게 입력해 주세요.")
    private String email;
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    @Size(min = 8, max = 20)
    private String password;
    private String subject;
    @NotBlank(message = "닉네임을 입력해 주세요.")
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
