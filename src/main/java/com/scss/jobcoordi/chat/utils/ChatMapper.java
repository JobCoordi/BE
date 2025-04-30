package com.scss.jobcoordi.chat.utils;

import com.scss.jobcoordi.chat.domain.ChatMessage;
import com.scss.jobcoordi.chat.domain.ChatRole;

public class ChatMapper {
    public static ChatMessage genChatMessage(String content, String uuid, ChatRole role) {
        return ChatMessage.builder()
                .content(content)
                .uuid(uuid)
                .role(role)
                .build();
    }

}
