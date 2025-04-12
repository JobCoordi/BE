package com.scss.jobcoordi.repository;

import org.springframework.stereotype.Repository;

@Repository
public class UserProfileRepository {
    // 저장
    public void save(){}

    // uuid? 받아서 값 반환
    public void findByUuid(String uuid){}

    // id 있나 확인
    public boolean existsById(Long id){
        return false;
    }

}
