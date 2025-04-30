package com.scss.jobcoordi.chat.dto;

import com.scss.jobcoordi.chat.domain.ChatMessage;
import com.scss.jobcoordi.chat.domain.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StartChatResponse {

    private Long ChatId;
    private String uuid;
    private String content;
    private LocalDateTime createdAt;

    public static StartChatResponse fromEntity(ChatMessage chatMessage){
        return StartChatResponse.builder()
                .ChatId(chatMessage.getChatId())
                .uuid(chatMessage.getUuid())
                .content(chatMessage.getContent())
                .createdAt(chatMessage.getCreatedAt())
                .build();
    }
}
