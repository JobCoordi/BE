package com.scss.jobcoordi.service;

import com.scss.jobcoordi.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    // 첫 채팅때 폼 보내고 답변받아서 주기 ( 데이터 둘다 저장 )
    public StartChatResponse startChat(StartChatRequest request){

        StartChatResponse response = new StartChatResponse();

        return response;
    }

    // 이후 일반 채팅
    public ChatResponse chat(ChatRequest request){

        ChatResponse response = new ChatResponse();

        return response;
    }


    // 아이디? 받아서 모든 채팅 내용 반환하기
    public List<ChatResponse> getAllMessagesByUuid(String uuid){

        return  List.of(new ChatResponse());
    }


    // ai와 통신. 데이터 전송
    public String callAiServer(){

        return null;
    }

    // 보낼 데이터 포메팅 formatMessageForAi


}
