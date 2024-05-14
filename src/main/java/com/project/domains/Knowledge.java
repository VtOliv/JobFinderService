package com.project.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Knowledge {
	
	private String name;
	
	private String schoolName;
	
	private String period;
}
