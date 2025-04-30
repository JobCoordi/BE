package com.scss.jobcoordi.chat.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StartChatRequest {
    private String educationLevel;
    @NotBlank(message = "내용을 입력해주세요.")
    private String major;
    @NotBlank(message = "내용을 입력해주세요.")
    private String interest;
    private String personality;
    private String preferredWork;
    private String desiredSalary;

}
