package com.scss.jobcoordi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Table(name = "user_profiles")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 36, nullable = false, unique = true)
    private String uuid;

    @Column(nullable = false)
    private Integer birthYear;

    @Column(length = 6, nullable = false)
    private String gender;

    @Column(length = 30, nullable = false)
    private String educationLevel;

    @Column(length = 60, nullable = false)
    private String major;

    @Column(length = 300, nullable = false)
    private String career;

    @Column(length = 300, nullable = false)
    private String interest;

    @Column(length = 300, nullable = false)
    private String certifications;

    @Column(length = 300, nullable = false)
    private String preferredWork;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String selfDescription;

    @Builder
    public UserProfile(Integer birthYear, String career, String certifications, String educationLevel, String gender, String interest, String preferredWork, String major, String selfDescription) {
        this.birthYear = (birthYear == null) ? 0 : birthYear;
        this.career = (career == null || career.isBlank()) ? "없음" : career;
        this.certifications = (certifications == null || certifications.isBlank()) ? "없음" : certifications;
        this.educationLevel = (educationLevel == null || educationLevel.isBlank()) ? "중학교 졸업" : educationLevel;
        this.gender = (gender == null || gender.isBlank()) ? "모름" : gender;
        this.interest = (interest == null || interest.isBlank()) ? "없음" : interest;
        this.preferredWork = (preferredWork == null || preferredWork.isBlank()) ? "없음" : preferredWork;
        this.major = (major == null || major.isBlank()) ? "없음" : major;
        this.selfDescription = (selfDescription == null || selfDescription.isBlank()) ? "없음" : selfDescription;
        this.uuid = UUID.randomUUID().toString();
    }
}
