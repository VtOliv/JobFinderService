package com.project.domains;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "apply")
public class Apply {
	
	@Id
	private Long id;

	private Long jobId;
	
	private String jobName;
	
	private Long userId;
	
	private LocalDateTime lastUpdate;
	
	private String applyMessage;
	
	private String replyMessage;
	
	private String status;
}
