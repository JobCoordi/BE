package com.scss.jobcoordi.repository;

import com.scss.jobcoordi.domain.ChatMessage;
import com.scss.jobcoordi.domain.ChatRole;
import com.scss.jobcoordi.dto.ChatResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatMessageRepository {
    // id, role 받아서 저장
    public ChatMessage save(ChatMessage chatMessage){

        return chatMessage;
    }

    // id 받아서 이전 n개의 채팅 꺼내오기 ( ai서버로 보낼 용도. n개는 정해질거임)
    public List<ChatMessage> findRecentMessagesByUuid(String uuid, int limit){

        return null;
    }

    // id로 모든 채팅 반환
    public List<ChatMessage> findAllMessagesByUuid(String uuid){

        return null;
    }




}

// 첫 채팅때 폼 저장과 보내고 답변받아서 주기

// 이후 일반 채팅

// ai와 통신. 데이터 전송

// 아이디? 받아서 모든 채팅 내용 반환하기