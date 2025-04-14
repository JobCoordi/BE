package com.scss.jobcoordi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_messages")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;

    @Column(length = 36, nullable = false)
    private String uuid;

    @Enumerated(EnumType.STRING)
    @Column(length = 9, nullable = false)
    private ChatRole role;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;


    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder
    public ChatMessage(String content, String uuid, ChatRole role) {
        this.content = (content == null || content.isBlank()) ? "내용 없음" : content;
        this.uuid = uuid;
        this.role = role;
    }
}
