package com.project.domains.form;

import java.util.List;

import com.project.domains.Knowledge;
import com.project.domains.PreviousWorks;
import com.project.domains.Skills;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailedForm {
	
	private Long userId;
	
	private String name;
	
	private String email;
	
	private String cpf;
	
	private String address;
	
	private String phoneNumber;
	
	private List<Knowledge> knowledge;
	
	private List<PreviousWorks> previousWorks;
	
	private List<Skills> skills;
	
	private String objective;
	
	private String about;
}