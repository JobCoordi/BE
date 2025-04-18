package com.scss.jobcoordi.repository;

import com.scss.jobcoordi.chat.domain.ChatMessage;
import com.scss.jobcoordi.chat.domain.ChatRole;
import com.scss.jobcoordi.chat.domain.UserProfile;
import com.scss.jobcoordi.chat.dto.ChatResponse;
import com.scss.jobcoordi.chat.repository.ChatMessageRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static com.scss.jobcoordi.chat.utils.Utils.genChatMessage;
import static org.assertj.core.api.Assertions.assertThat;


//@SpringBootTest
@Transactional
@DataJpaTest
@Slf4j
class ChatMessageRepositoryTests {
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Test
    @DisplayName("save test")
    void save_test() throws Exception {
        UserProfile u1 = UserProfile.builder().birthYear(1999).build();
        ChatMessage userQuestion = genChatMessage("user test 1번임", u1.getUuid(), ChatRole.user);
        ChatMessage chatSaved = chatMessageRepository.save(userQuestion);

        log.info("chatSaved.toString() = {}", chatSaved.toString());


    }

    @Test
    @DisplayName("여러개 잘 가져오나")
    void test1() throws Exception {

        UserProfile u1 = UserProfile.builder().birthYear(1999).build();

        ChatMessage userQuestion = genChatMessage("user test 1번임", u1.getUuid(), ChatRole.user);
        chatMessageRepository.save(userQuestion);
        ChatMessage aiQuestion = genChatMessage("ai test 1번임", u1.getUuid(), ChatRole.assistant);
        chatMessageRepository.save(aiQuestion);

        ChatMessage userQuestion2 = genChatMessage("user test 2", u1.getUuid(), ChatRole.user);
        chatMessageRepository.save(userQuestion2);
        ChatMessage aiQuestion2 = genChatMessage("ai test 2", u1.getUuid(), ChatRole.assistant);
        chatMessageRepository.save(aiQuestion2);

        List<ChatResponse> allChat = chatMessageRepository.findAllMessagesByUuid(userQuestion.getUuid());
        log.info("allChat = {}", allChat);
        assertThat(allChat.size()).isEqualTo(4);

    }


}