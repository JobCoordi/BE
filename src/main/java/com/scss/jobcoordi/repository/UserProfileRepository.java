package com.scss.jobcoordi.repository;

import com.scss.jobcoordi.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    boolean existsByUuid(String uid);


}
