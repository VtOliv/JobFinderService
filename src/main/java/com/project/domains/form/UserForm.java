package com.project.domains.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {
	
	private String password;
	private String name;
	private String email;
	private String cpf;
	private Boolean isRecruiter;
}
