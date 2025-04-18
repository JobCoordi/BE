package com.scss.jobcoordi.chat.dto;

import com.scss.jobcoordi.chat.domain.ChatRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleContentDto {
    private ChatRole role;
    private String content;
}
