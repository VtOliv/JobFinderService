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
@Document(collection = "usuario")
public class User {
	
	@Id
	private Long id;
	
	private String password;
	
	private String name;
	
	private String email;
	
	private String cpf;
	
	private Boolean isRecruiter;
}
