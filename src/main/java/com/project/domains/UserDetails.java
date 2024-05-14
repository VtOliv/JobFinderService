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
@Document(collection = "usuarioDetalhe")
public class UserDetails {
	
	@Id
	private Long id;
	
	private Long userId;
	
	private String adress;
	
	private String phoneNumber;
	
	private List<Knowledge> knowledge;
	
	private List<PreviousWorks> previousWorks;
	
	private List<Skills> skills;
	
	private String objective;
	
	private String about;
}
