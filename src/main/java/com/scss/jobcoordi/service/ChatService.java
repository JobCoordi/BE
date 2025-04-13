package com.scss.jobcoordi.service;

import com.scss.jobcoordi.domain.ChatMessage;
import com.scss.jobcoordi.domain.ChatRole;
import com.scss.jobcoordi.domain.UserProfile;
import com.scss.jobcoordi.dto.*;
import com.scss.jobcoordi.repository.ChatMessageRepository;
import com.scss.jobcoordi.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    public final UserProfileRepository userProfileRepository;
    public final ChatMessageRepository chatMessageRepository;

    // 첫 채팅때 폼 보내고 답변받아서 주기 ( 데이터 둘다 저장 )
    public StartChatResponse startChat(StartChatRequest request){
        UserProfile userProfile = getUserProfile(request);

        UserProfile saved = userProfileRepository.save(userProfile);

        String userContent = formatProfile(saved);

        ChatMessage userQuestion = getChatMessage(userContent, saved.getUuid(), ChatRole.user);

        String aiContent = callAiServer(userQuestion.getContent());


        ChatMessage aiAnswer = getChatMessage(aiContent, saved.getUuid(), ChatRole.assistant);

        chatMessageRepository.save(userQuestion);
        ChatMessage answerSaved = chatMessageRepository.save(aiAnswer);

        return StartChatResponse.builder()
                .ChatId(answerSaved.getChatId())
                .uuid(answerSaved.getUuid())
                .content(answerSaved.getContent())
                .createdAt(answerSaved.getCreatedAt())
                .build();
    }

    // 이후 일반 채팅

    public ChatResponse chat(ChatRequest request){

        ChatResponse response = new ChatResponse();

        return response;
    }

    // 아이디? 받아서 모든 채팅 내용 반환하기

    public List<ChatResponse> findAllMessagesByUuid(String uuid){

        return  List.of(new ChatResponse());
    }

    // ai와 통신

    public String callAiServer(String profile){

        return null;
    }


    public String formatProfile(UserProfile userProfile){
        String profile = "출생년도 : "+userProfile.getBirthYear()+", 성별 : "+userProfile.getGender();
        return profile;
    }

    // 보낼 데이터 포메팅 formatMessageForAi


    private static UserProfile getUserProfile(StartChatRequest request) {
        return UserProfile.builder()
                .birthYear(request.getBirthYear())
                .gender(request.getGender())
                .educationLevel(request.getEducationLevel())
                .major(request.getMajor())
                .career(request.getCareer())
                .interest(request.getInterest())
                .certifications(request.getCertifications())
                .preferredWork(request.getPreferredWork())
                .selfDescription(request.getSelfDescription())
                .build();
    }

    private static ChatMessage getChatMessage(String content, String uuid, ChatRole role) {
        return ChatMessage.builder()
                .content(content)
                .uuid(uuid)
                .role(role)
                .build();
    }

}
