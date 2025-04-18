package com.scss.jobcoordi.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {
    private Long ChatId;
    private String content;
    private LocalDateTime createdAt;
}
