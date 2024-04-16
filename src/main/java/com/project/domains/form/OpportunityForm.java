package com.project.domains.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpportunityForm {
		
	private String jobName;	
	private String description;	
	private String shortDescription;	
	private String companyName;	
	private String income;	
	private String officeHour;
}
