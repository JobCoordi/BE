package com.scss.jobcoordi.repository;

import com.scss.jobcoordi.domain.ChatMessage;
import com.scss.jobcoordi.dto.ChatResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    // 모든 채팅 반환
    @Query("select new com.scss.jobcoordi.dto.ChatResponse(c.chatId, c.content, c.createdAt) from ChatMessage c where c.uuid = :uuid")
    List<ChatResponse> findAllMessagesByUuid(String uuid);


}
