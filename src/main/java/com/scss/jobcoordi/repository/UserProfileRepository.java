package com.scss.jobcoordi.repository;

import com.scss.jobcoordi.domain.UserProfile;
import org.springframework.stereotype.Repository;

@Repository
public class UserProfileRepository {
    // 저장
    public UserProfile save(UserProfile user) {
        return user;
    }

    // uuid? 받아서 값 반환
    public UserProfile findByUuid(String uuid){

        return null;
    }

    // id 있나 확인
//    public boolean existsById(Long id){
//        return false;
//    }

}
