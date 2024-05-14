package com.project.domains.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyForm {

	private Long jobId;
	
	private Long userId;
	
	private String applyMessage;
}
