package com.scss.jobcoordi.chat.repository;

import com.scss.jobcoordi.chat.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    boolean existsByUuid(String uid);


}
