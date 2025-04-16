package com.scss.jobcoordi.repository;

import com.scss.jobcoordi.domain.UserProfile;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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

        UserProfile u1 = UserProfile.builder().birthYear(1999).selfDescription("입력해야함").build();
        UserProfile saved = repository.save(u1);
        log.info("saved: {}", saved);
        assertThat(saved.getId()).isEqualTo(u1.getId());


    }

}