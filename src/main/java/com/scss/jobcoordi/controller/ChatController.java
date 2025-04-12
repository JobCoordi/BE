package com.scss.jobcoordi.controller;


import com.scss.jobcoordi.dto.*;
import com.scss.jobcoordi.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/chat")
@RestController
@RequiredArgsConstructor
public class ChatController {
    public final ChatService chatService;

    // 첫 채팅
    @PostMapping("/start")
    public ResponseEntity<StartChatResponse> startChat(@RequestBody StartChatRequest request){
        return ResponseEntity.ok().body(chatService.startChat(request));
    }

    // 일반 채팅
    @PostMapping("/message")
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request){

        return ResponseEntity.ok().body(chatService.chat(request));
    }

    // 채팅 목록 조회
    @GetMapping("/messages")
    public ResponseEntity<List<ChatResponse>> getAllMessages(String uuid){

        return ResponseEntity.ok().body(chatService.getAllMessagesByUuid(uuid));
    }

}
