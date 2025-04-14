package com.scss.jobcoordi.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scss.jobcoordi.domain.ChatMessage;
import com.scss.jobcoordi.domain.ChatRole;
import com.scss.jobcoordi.domain.UserProfile;
import com.scss.jobcoordi.dto.RoleContentDto;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;

import static com.scss.jobcoordi.utils.Utils.genChatMessage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
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
    @DisplayName("6개 잘 가져오나")
    void test1() throws Exception {

        UserProfile u1 = UserProfile.builder().birthYear(1999).build();

        ChatMessage userQuestion = genChatMessage("user test 1번임", u1.getUuid(), ChatRole.user);
        ChatMessage chatSaved = chatMessageRepository.save(userQuestion);
        ChatMessage aiQuestion = genChatMessage("ai test 1번임", u1.getUuid(), ChatRole.assistant);
        ChatMessage aiSaved = chatMessageRepository.save(aiQuestion);

        ChatMessage userQuestion2 = genChatMessage("user test 2", u1.getUuid(), ChatRole.user);
        ChatMessage chatSaved2 = chatMessageRepository.save(userQuestion2);
        ChatMessage aiQuestion2 = genChatMessage("ai test 2", u1.getUuid(), ChatRole.assistant);
        ChatMessage aiSaved2 = chatMessageRepository.save(aiQuestion2);


        ChatMessage userQuestion3 = genChatMessage("user test 3", u1.getUuid(), ChatRole.user);
        ChatMessage chatSaved3 = chatMessageRepository.save(userQuestion3);
        ChatMessage aiQuestion3 = genChatMessage("ai test 3", u1.getUuid(), ChatRole.assistant);
        ChatMessage aiSaved3 = chatMessageRepository.save(aiQuestion3);

        ChatMessage userQuestion4 = genChatMessage("user test 4", u1.getUuid(), ChatRole.user);
        ChatMessage chatSaved4 = chatMessageRepository.save(userQuestion4);
        ChatMessage aiQuestion4 = genChatMessage("ai test 4", u1.getUuid(), ChatRole.assistant);
        ChatMessage aiSaved4 = chatMessageRepository.save(aiQuestion4);



        List<RoleContentDto> recentMessagesByUuid = chatMessageRepository.findRecentMessagesByUuid(chatSaved.getUuid(), PageRequest.of(0, 6));
        for (RoleContentDto roleContentDto : recentMessagesByUuid) {
            log.info("roleContentDto.toString() = {}", roleContentDto.toString());
        }
        log.info("");
        Collections.reverse(recentMessagesByUuid);
        for (RoleContentDto roleContentDto : recentMessagesByUuid) {
            log.info("roleContentDto.toString() = {}", roleContentDto.toString());
        }

        log.info("");
        log.info("recentMessagesByUuid = {}", new ObjectMapper().writeValueAsString(recentMessagesByUuid));


    }


}