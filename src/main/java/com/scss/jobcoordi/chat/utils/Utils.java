package com.scss.jobcoordi.chat.utils;

import com.scss.jobcoordi.chat.domain.ChatMessage;
import com.scss.jobcoordi.chat.domain.ChatRole;
import com.scss.jobcoordi.chat.domain.UserProfile;
import com.scss.jobcoordi.chat.dto.StartChatRequest;

import java.util.UUID;

public class Utils {

    // 프로필 문자열로 포매팅
    public static String formatProfile(UserProfile userProfile){

        return "최종학력 : " + userProfile.getEducationLevel()
                + "\n전공 : " + userProfile.getMajor()
                + "\n관심분야 : " + userProfile.getInterest()
                + "\n성격 : " + userProfile.getPersonality()
                + "\n희망근무형태 : " + userProfile.getPreferredWork()
                + "\n희망연봉 : " + userProfile.getDesiredSalary();
    }


}
