package com.scss.jobcoordi.chat.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "user_profiles")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 36, nullable = false, unique = true)
    private String uuid;

    @Column(length = 30, nullable = false)
    private String educationLevel;

    @Column(length = 60, nullable = false)
    private String major;

    @Column(length = 300, nullable = false)
    private String interest;

    @Column(length = 15, nullable = false)
    private String personality;

    @Column(length = 30, nullable = false)
    private String preferredWork;

    @Column(length = 20, nullable = false)
    private String desiredSalary;

    @Builder
    public UserProfile(String desiredSalary, String educationLevel, String interest, String major, String personality, String preferredWork, String uuid) {
        this.desiredSalary = desiredSalary;
        this.educationLevel = educationLevel;
        this.interest = interest;
        this.major = major;
        this.personality = personality;
        this.preferredWork = preferredWork;
        this.uuid = uuid;
    }
}
