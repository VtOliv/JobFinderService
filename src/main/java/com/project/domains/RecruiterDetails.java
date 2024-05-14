package com.project.domains;

import java.util.List;

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
@Document(collection = "recruiterDetails")
public class RecruiterDetails {
	
	@Id
	private Long id;
	
	private List<Long> myOpportunitiesId;
	
	private String cnpj;
	
	private String corpEmail;
}
