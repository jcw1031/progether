package com.woopaca.progether.entity;

import com.woopaca.progether.controller.user.dto.ProfileUpdateRequestDto;
import com.woopaca.progether.controller.user.dto.SignUpRequestDto;
import com.woopaca.progether.controller.user.dto.UserProfileResponseDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private String introduction;
    private String website;
    private int postsNumber;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "skill", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "skill_name")
    private List<String> skills;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.REMOVE)
    private List<Post> posts = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private Part part;

    @Builder
    public User(String email, String password, String subject, String name,
                String introduction, String website, int postsNumber) {
        this.email = email;
        this.password = password;
        this.subject = subject;
        this.name = name;
        this.introduction = introduction;
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

    public void updateUserProfile(ProfileUpdateRequestDto profileUpdateRequestDto) {
        subject = profileUpdateRequestDto.getSubject();
        introduction = profileUpdateRequestDto.getIntroduction();
        website = profileUpdateRequestDto.getWebsite();
        part = profileUpdateRequestDto.getPart();
    }

    public UserProfileResponseDto toProfileDto() {
        String introduction = this.introduction;
        if (this.introduction != null) {
            introduction = this.introduction.replace("\n", "</br>");
        }
        return UserProfileResponseDto.builder()
                .email(email)
                .name(name)
                .subject(subject)
                .website(website)
                .introduction(introduction)
                .postNumber(postsNumber)
                .part(part)
                .skills(skills)
                .build();
    }
}
