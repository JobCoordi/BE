package com.scss.jobcoordi.chat.repository;

import com.scss.jobcoordi.chat.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    boolean existsByUuid(String uid);
    Optional<UserProfile> findByUuid(String uuid);


}
