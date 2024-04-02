package com.project.domains;

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
@Document(collection = "vagas")
public class Opportunity {
	
	@Id
	private Long id;
	
	private String jobName;
	
	private String description;
	
	private String shortDescription;
	
	private String companyName;
	
	private String income;
	
	private String officeHour;
}
