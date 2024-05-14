package com.project.domains.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecruiterOverallView {

	private Integer opportunityCount;
	private Integer appliesCount;
	private Integer repliesCount;
}