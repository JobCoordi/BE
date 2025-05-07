package com.scss.jobcoordi.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AiRequest {
	private String user_id;
	private String content;
}
