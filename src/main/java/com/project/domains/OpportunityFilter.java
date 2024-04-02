package com.project.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpportunityFilter {
	
	private Long id;
	
	private String jobName;
	
	private String companyName;
	
	private String officeHour;
}
