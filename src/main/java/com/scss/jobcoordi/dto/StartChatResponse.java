package com.scss.jobcoordi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StartChatResponse {

    private Long ChatId;
    private String uuid;
    private String content;
    private LocalDateTime createdAt;

}
