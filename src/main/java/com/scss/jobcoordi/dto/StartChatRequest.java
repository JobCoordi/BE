package com.scss.jobcoordi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StartChatRequest {
    private int birthYear;
    private String gender;
    private String educationLevel;
    private String major;
    private String career;
    private String interest;
    private String certifications;
    private String preferredWork;
    @NotBlank(message = "내용을 입력해주세요.")
    private String selfDescription;

}
