package com.scss.jobcoordi.dto;

import com.scss.jobcoordi.domain.ChatRole;
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
