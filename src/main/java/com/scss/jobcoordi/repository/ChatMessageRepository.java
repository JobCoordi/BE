package com.scss.jobcoordi.repository;

import com.scss.jobcoordi.domain.ChatMessage;
import com.scss.jobcoordi.dto.ChatResponse;
import org.hibernate.query.spi.Limit;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    // id 받아서 이전 n개의 채팅 꺼내오기 ( ai서버로 보낼 용도. 최근 순서라 반환받으면 뒤집어야함)
    @Query("select c from ChatMessage c where c.uuid = :uuid order by c.chatId desc")
    List<ChatMessage> findRecentMessagesByUuid(String uuid, Pageable pageable);

    // id로 모든 채팅 반환
    @Query("select new com.scss.jobcoordi.dto.ChatResponse(c.chatId, c.content, c.createdAt) from ChatMessage c where c.uuid = :uuid")
    List<ChatResponse> findAllMessagesByUuid(String uuid);


}
