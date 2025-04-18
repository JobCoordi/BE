package com.scss.jobcoordi.chat.domain;

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

    // 설계 변경으로 프론트에서도 사용 안하면 삭제 예정
    @Enumerated(EnumType.STRING)
    @Column(length = 9, nullable = false)
    private ChatRole role;

    @Column(columnDefinition = "MEDIUMTEXT", nullable = false)
    private String content;


    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder
    public ChatMessage(String content, String uuid, ChatRole role) {
        this.content = (content == null || content.isBlank()) ? "내용 없음" : content;
        this.uuid = uuid;
        this.role = role;
    }
}
