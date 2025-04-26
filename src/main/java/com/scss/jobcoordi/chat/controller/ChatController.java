package com.scss.jobcoordi.chat.controller;


import com.scss.jobcoordi.chat.dto.ChatRequest;
import com.scss.jobcoordi.chat.dto.ChatResponse;
import com.scss.jobcoordi.chat.dto.StartChatRequest;
import com.scss.jobcoordi.chat.dto.StartChatResponse;
import com.scss.jobcoordi.chat.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/chat")
@RestController
@RequiredArgsConstructor
public class ChatController {
    public final ChatService chatService;

    // 첫 채팅
    @PostMapping("/start")
    public ResponseEntity<StartChatResponse> startChat(@RequestBody StartChatRequest request){
        log.info("ChatController.startChat 시작채팅");
        return ResponseEntity.ok().body(chatService.startChat(request));
    }

    // 일반 채팅
    @PostMapping("/message")
    public ResponseEntity<ChatResponse> chat(@RequestBody @Valid ChatRequest request){
        log.info("ChatController.message 일반 채팅");
        return ResponseEntity.ok().body(chatService.chat(request));
    }

    // 채팅 목록 조회
    @GetMapping("/messages")
    public ResponseEntity<List<ChatResponse>> getAllMessages(String uuid){
        log.info("ChatController.messages 채팅 조회");
        return ResponseEntity.ok().body(chatService.findAllMessagesByUuid(uuid));
    }


}
