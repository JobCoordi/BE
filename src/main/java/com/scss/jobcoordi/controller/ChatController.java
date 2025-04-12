package com.scss.jobcoordi.controller;


import com.scss.jobcoordi.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/chat")
@RestController
public class ChatController {

    // 첫 채팅
    @PostMapping("/start")
    public ResponseEntity<StartChatResponse> startChat(@RequestBody StartChatRequest request){

        // 서비스에서 보내서 응답 가져오기

        StartChatResponse response = new StartChatResponse(); // 값 담기. 우선 그냥 해놨음
        return ResponseEntity.ok().body(response);
    }

    // 일반 채팅
    @PostMapping("/message")
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request){

        // 서비스에서 보내서 응답 가져오기

        ChatResponse response = new ChatResponse(); // 값 담기. 우선 그냥 해놨음

        return ResponseEntity.ok().body(response);
    }

    // 채팅 목록 조회
    @GetMapping("/messages")
    public ResponseEntity<ChatMessageListResponse> getAllMessages(String uuid){

        // 서비스에 조회 요청

        ChatMessageListResponse response = new ChatMessageListResponse();



        return ResponseEntity.ok().body(response);
    }

}
