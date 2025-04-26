package com.scss.jobcoordi.chat.utils;

import com.scss.jobcoordi.chat.domain.ChatMessage;
import com.scss.jobcoordi.chat.domain.ChatRole;
import com.scss.jobcoordi.chat.domain.UserProfile;
import com.scss.jobcoordi.chat.dto.StartChatRequest;

public class Utils {

    public static UserProfile genUserProfile(StartChatRequest request) {
        return UserProfile.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .birthYear(request.getBirthYear())
                .gender(request.getGender())
                .educationLevel(request.getEducationLevel())
                .major(request.getMajor())
                .career(request.getCareer())
                .interest(request.getInterest())
                .certifications(request.getCertifications())
                .preferredWork(request.getPreferredWork())
                .selfDescription(request.getSelfDescription())
                .build();
    }

    public static ChatMessage genChatMessage(String content, String uuid, ChatRole role) {
        return ChatMessage.builder()
                .content(content)
                .uuid(uuid)
                .role(role)
                .build();
    }

    // 프로필 문자열로 포매팅
    public static String formatProfile(UserProfile userProfile){
        String profile = "이름 : " + userProfile.getUsername()
                + "\n이메일 : " + userProfile.getEmail()
                + "\n ... 등등 form에서 받은 내용들 추가";
        return profile;
    }


}
