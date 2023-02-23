package com.woopaca.progether.entity;

import com.woopaca.progether.controller.user.dto.SignUpRequestDto;
import com.woopaca.progether.controller.user.dto.UserProfileResponseDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

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
    private String part;
    private String skills;
    private String introduce;
    private String website;
    private int postsNumber;

    @Builder
    public User(String email, String password, String subject, String name, String part, String skills,
                String introduce, String website, int postsNumber) {
        this.email = email;
        this.password = password;
        this.subject = subject;
        this.name = name;
        this.part = part;
        this.skills = skills;
        this.introduce = introduce;
        this.website = website;
        this.postsNumber = postsNumber;
    }

    public static User from(SignUpRequestDto signUpRequestDto) {
        return User.builder()
                .email(signUpRequestDto.getEmail())
                .password(BCrypt.hashpw(signUpRequestDto.getPassword(), BCrypt.gensalt()))
                .name(signUpRequestDto.getName())
                .build();
    }

    public UserProfileResponseDto toProfileDto() {
        return UserProfileResponseDto.builder()
                .email(email)
                .name(name)
                .part(part)
                .subject(subject)
                .website(website)
                .postNumber(postsNumber)
                .build();
    }
}
