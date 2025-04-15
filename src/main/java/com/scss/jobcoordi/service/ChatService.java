package com.scss.jobcoordi.service;

import com.scss.jobcoordi.domain.ChatMessage;
import com.scss.jobcoordi.domain.ChatRole;
import com.scss.jobcoordi.domain.UserProfile;
import com.scss.jobcoordi.dto.*;
import com.scss.jobcoordi.repository.ChatMessageRepository;
import com.scss.jobcoordi.repository.UserProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.scss.jobcoordi.utils.Utils.*;

@Service
@RequiredArgsConstructor
public class ChatService {

    public final UserProfileRepository userProfileRepository;
    public final ChatMessageRepository chatMessageRepository;

    // 첫 채팅때 폼 보내고 답변받아서 주기 ( 데이터 둘다 저장 )
    @Transactional
    public StartChatResponse startChat(StartChatRequest request){
        // 프로필 저장
        UserProfile userSaved = userProfileRepository.save(genUserProfile(request));

        // content 형식으로 변환
        String userContent = formatProfile(userSaved);

        ChatMessage resp = saveAndCallAi(userSaved.getUuid(), userContent);

        return StartChatResponse.builder()
                .ChatId(resp.getChatId())
                .uuid(resp.getUuid())
                .content(resp.getContent())
                .createdAt(resp.getCreatedAt())
                .build();
    }

    // 이후 일반 채팅
    @Transactional
    public ChatResponse chat(ChatRequest request){

        ChatMessage resp = saveAndCallAi(request.getUuid(), request.getContent());

        return new ChatResponse(resp.getChatId(), resp.getContent(), resp.getCreatedAt());
    }

    private ChatMessage saveAndCallAi(String uuid, String content){
        // user 메시지 저장
        ChatMessage questionSaved = chatMessageRepository.save(
                genChatMessage(content, uuid, ChatRole.user)
        );

        // 챗봇 서버 호출 후 답변 저장 및 반환
        String aiContent = callAiServer(questionSaved.getUuid(), questionSaved.getContent());

        return chatMessageRepository.save(
                genChatMessage(aiContent, questionSaved.getUuid(), ChatRole.assistant)
        );
    }

    // 예외처리 해야함
    // 챗봇 서버와 통신
    public String callAiServer(String uuid, String content){
        // 두 값 주면서 답변받기

        return "챗봇 서버에서 받은 임시 값이 표기 될 예정입니다.";
    }


    // 아이디? 받아서 모든 채팅 내용 반환
    public List<ChatResponse> findAllMessagesByUuid(String uuid){

        return chatMessageRepository.findAllMessagesByUuid(uuid);
    }





}
