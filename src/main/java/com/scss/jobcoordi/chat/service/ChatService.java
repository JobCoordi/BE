package com.scss.jobcoordi.chat.service;

import com.scss.jobcoordi.chat.domain.ChatMessage;
import com.scss.jobcoordi.chat.domain.ChatRole;
import com.scss.jobcoordi.chat.domain.UserProfile;
import com.scss.jobcoordi.chat.dto.AiRequest;
import com.scss.jobcoordi.chat.dto.AiResponse;
import com.scss.jobcoordi.chat.dto.ChatRequest;
import com.scss.jobcoordi.chat.dto.ChatResponse;
import com.scss.jobcoordi.chat.dto.StartChatRequest;
import com.scss.jobcoordi.chat.dto.StartChatResponse;
import com.scss.jobcoordi.chat.exceptions.AiServiceException;
import com.scss.jobcoordi.chat.exceptions.UuidNotFoundException;
import com.scss.jobcoordi.chat.repository.ChatMessageRepository;
import com.scss.jobcoordi.chat.repository.UserProfileRepository;
import com.scss.jobcoordi.chat.utils.ChatMapper;
import com.scss.jobcoordi.chat.utils.UserProfileMapper;
import com.scss.jobcoordi.chat.utils.Utils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    public final UserProfileRepository userProfileRepository;
    public final ChatMessageRepository chatMessageRepository;
    private final WebClient webClient;

    // 첫 채팅때 폼 보내고 답변받아서 주기 ( 데이터 둘다 저장 )
    @Transactional
    public StartChatResponse startChat(StartChatRequest request) {
        // 프로필 저장
        UserProfile userSaved = userProfileRepository.save(UserProfileMapper.toEntity(request));

        // content 형식으로 변환
        String userContent = Utils.formatProfile(userSaved);

        ChatMessage resp = saveAndCallAi(userSaved.getUuid(), userContent);

        return StartChatResponse.fromEntity(resp);
    }

    // 이후 일반 채팅
    @Transactional
    public ChatResponse chat(ChatRequest request) {

        ChatMessage resp = saveAndCallAi(request.getUuid(), request.getContent());

        return new ChatResponse(resp.getChatId(), resp.getContent(), resp.getCreatedAt());
    }

    private ChatMessage saveAndCallAi(String uuid, String content) {
        // uuid 검증
        if (!userProfileRepository.existsByUuid(uuid)) {
            throw new UuidNotFoundException("해당 사용자가 없습니다. 폼데이터를 제출해주세요.");
        }
        // user 메시지 저장
        ChatMessage questionSaved = chatMessageRepository.save(
                ChatMapper.genChatMessage(content, uuid, ChatRole.user)
        );

        // 챗봇 서버 호출 후 답변 저장 및 반환
        String aiContent = callAiServer(questionSaved.getUuid(), questionSaved.getContent());

        return chatMessageRepository.save(
                ChatMapper.genChatMessage(aiContent, questionSaved.getUuid(), ChatRole.assistant)
        );
    }

    // 예외처리 해야함
    // 챗봇 서버와 통신
    public String callAiServer(String uuid, String content) {
        AiRequest request = new AiRequest(uuid, content);
        try {
            return webClient.post()
                .uri("https://congenial-meme-5jpxj9jqv47hp6g7-8000.app.github.dev/chat")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(AiResponse.class)
                .block()
                .getResponse();
        } catch (Exception e) {
            throw new AiServiceException("챗봇 서버 쪽 에러인데 아직 안해봐서 모름");
        }
    }


    // 아이디? 받아서 모든 채팅 내용 반환
    public List<ChatResponse> findAllMessagesByUuid(String uuid) {
        List<ChatResponse> allMessagesByUuid = chatMessageRepository.findAllMessagesByUuid(uuid);
        if (allMessagesByUuid.isEmpty()) {
            throw new UuidNotFoundException("해당 사용자의 채팅 기록이 없습니다.");
        }
        return allMessagesByUuid;
    }


}
