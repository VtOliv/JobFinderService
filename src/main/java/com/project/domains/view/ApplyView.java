package com.project.domains.view;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyView {

	private Long id;
	private Long jobId;
	private String jobName;
	private Long userId;
	private LocalDateTime lastUpdate;
	private String applyMessage;
	private String replyMessage;
	private String status;
}