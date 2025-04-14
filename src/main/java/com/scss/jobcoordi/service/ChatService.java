package com.scss.jobcoordi.service;

import com.scss.jobcoordi.domain.ChatMessage;
import com.scss.jobcoordi.domain.ChatRole;
import com.scss.jobcoordi.domain.UserProfile;
import com.scss.jobcoordi.dto.*;
import com.scss.jobcoordi.repository.ChatMessageRepository;
import com.scss.jobcoordi.repository.UserProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.scss.jobcoordi.utils.Utils.genChatMessage;
import static com.scss.jobcoordi.utils.Utils.genUserProfile;

@Service
@RequiredArgsConstructor
public class ChatService {

    public final UserProfileRepository userProfileRepository;
    public final ChatMessageRepository chatMessageRepository;

    // 첫 채팅때 폼 보내고 답변받아서 주기 ( 데이터 둘다 저장 )
    @Transactional
    public StartChatResponse startChat(StartChatRequest request){
        // 프로필 저장
        UserProfile userProfile = genUserProfile(request);
        UserProfile userSaved = userProfileRepository.save(userProfile);

        // content 형식으로 변환
        String userContent = formatProfile(userSaved);

        // user 메시지 저장
        ChatMessage userQuestion = genChatMessage(userContent, userSaved.getUuid(), ChatRole.user);
        ChatMessage chatSaved = chatMessageRepository.save(userQuestion);

        // ai 결과 저장
        String aiContent = callAiServer(userQuestion.getContent());
        ChatMessage aiAnswer = genChatMessage(aiContent, chatSaved.getUuid(), ChatRole.assistant);
        ChatMessage answerSaved = chatMessageRepository.save(aiAnswer);

        return StartChatResponse.builder()
                .ChatId(answerSaved.getChatId())
                .uuid(answerSaved.getUuid())
                .content(answerSaved.getContent())
                .createdAt(answerSaved.getCreatedAt())
                .build();
    }

    // 이후 일반 채팅
    @Transactional
    public ChatResponse chat(ChatRequest request){

        // user 메시지 저장
        ChatMessage userQuestion = genChatMessage(request.getContent(), request.getUuid(), ChatRole.user);
        ChatMessage chatSaved = chatMessageRepository.save(userQuestion);

        // 최근 n개의 채팅기록 포멧팅해서 보내야함. 우선 6개로, 짝수여야할듯.
        List<RoleContentDto> recentMessagesByUuid = chatMessageRepository.findRecentMessagesByUuid(chatSaved.getUuid(), PageRequest.of(0, 6));
        Collections.reverse(recentMessagesByUuid);

        // ai 결과 저장
        String aiContent = callAiServer(userQuestion.getContent());
        ChatMessage aiAnswer = genChatMessage(aiContent, chatSaved.getUuid(), ChatRole.assistant);
        ChatMessage answerSaved = chatMessageRepository.save(aiAnswer);

        return new ChatResponse(answerSaved.getChatId(), answerSaved.getContent(), answerSaved.getCreatedAt());
    }

    // 아이디? 받아서 모든 채팅 내용 반환하기
    public List<ChatResponse> findAllMessagesByUuid(String uuid){

        return chatMessageRepository.findAllMessagesByUuid(uuid);

    }

    // ai와 통신

    public String callAiServer(String profile){




        return "임시 값";
    }


    public String formatProfile(UserProfile userProfile){
        String profile = "출생년도 : "+userProfile.getBirthYear()+", 성별 : "+userProfile.getGender();
        return profile;
    }

    // 보낼 데이터 포메팅 formatMessageForAi




}
