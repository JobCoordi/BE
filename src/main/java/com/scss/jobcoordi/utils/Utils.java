package com.scss.jobcoordi.utils;

import com.scss.jobcoordi.domain.ChatMessage;
import com.scss.jobcoordi.domain.ChatRole;
import com.scss.jobcoordi.domain.UserProfile;
import com.scss.jobcoordi.dto.StartChatRequest;

public class Utils {

    public static UserProfile genUserProfile(StartChatRequest request) {
        return UserProfile.builder()
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
        String profile = "출생년도 : " + userProfile.getBirthYear()
                + "\n성별 : " + userProfile.getGender()
                + "\n ..."
                + "\n전공 : " + userProfile.getMajor()
                + "\n그 외 : " + userProfile.getSelfDescription();
        return profile;
    }


}
