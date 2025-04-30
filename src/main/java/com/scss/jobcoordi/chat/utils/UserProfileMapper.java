package com.scss.jobcoordi.chat.utils;

import com.scss.jobcoordi.chat.domain.UserProfile;
import com.scss.jobcoordi.chat.dto.StartChatRequest;

import java.util.UUID;

public class UserProfileMapper {

    public static UserProfile toEntity(StartChatRequest request){
        return UserProfile.builder()
                .uuid(UUID.randomUUID().toString())
                .educationLevel(request.getEducationLevel())
                .major(request.getMajor())
                .interest(request.getInterest())
                .personality(request.getPersonality())
                .preferredWork(request.getPreferredWork())
                .desiredSalary(request.getDesiredSalary())
                .build();
    }


}
