package com.scss.jobcoordi.repository;

import com.scss.jobcoordi.chat.domain.UserProfile;
import com.scss.jobcoordi.chat.repository.UserProfileRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
//@DataJpaTest
@Slf4j
class UserProfileRepositoryTests {
    @Autowired
    private UserProfileRepository repository;

    @Test
    @DisplayName("저장 테스트")
    void 저장() throws Exception {

        UserProfile u1 = UserProfile.builder()
                            .uuid(UUID.randomUUID().toString())
                            .educationLevel("대학교")
                            .major("컴공")
                            .interest("없음")
                            .personality("내향적")
                            .preferredWork("주택")
                            .desiredSalary("3000~3500")
                            .build();
        UserProfile saved = repository.save(u1);
        log.info("saved: {}", saved);
        assertThat(saved.getId()).isEqualTo(u1.getId());


    }

}