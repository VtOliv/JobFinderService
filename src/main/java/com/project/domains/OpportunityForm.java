package com.project.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpportunityForm {
	
	private Long id;
	
	private String jobName;
	
	private String description;
	
	private String shortDescription;
	
	private String companyName;
	
	private String income;
	
	private String officeHour;
}
