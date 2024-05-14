package com.project.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.domains.User;
import com.project.domains.UserDetails;
import com.project.domains.form.LoginForm;
import com.project.domains.form.UserDetailedForm;
import com.project.domains.form.UserForm;
import com.project.domains.view.UserDetailedView;
import com.project.domains.view.UserView;
import com.project.repository.UserDetailedRepository;
import com.project.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final ModelMapper mapper;
	private final UserRepository repository;
	private final UserDetailedRepository detailsRepository;

	public User create(UserForm form) {
		var domain = mapper.map(form, User.class);
		var count = repository.count();
		
		domain.setId(count + 1);
		domain.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));

		return repository.save(domain);
	}

	public User update(UserForm form, Long id) {
		var result = repository.findById(id).orElse(null);

		if (result != null) {
			result.setName(form.getName());
			result.setCpf(form.getCpf());
			result.setEmail(form.getEmail());
		}

		return repository.save(result);
	}

	private User findUser(String email) {
		return repository.findByEmail(email);
	}
	
	public User findUserbyId(Long id) {
		return repository.findById(id).orElse(null);
	}

	public UserView loginUser(LoginForm form) {
		var user = findUser(form.getEmail());
		Boolean result = false;
		var encoder = new BCryptPasswordEncoder();

		if (user != null) {
			result = user.getEmail().equals(form.getEmail()) && encoder.matches(form.getPassword(), user.getPassword())
					? true
					: false;
		}
		
		return UserView.builder()
				.id(user.getId())
				.name(user.getName())
				.role(user.getIsRecruiter() ? "Recrutador" : "Usuario")
				.result(result? "success" : "failure")
				.build();
	}
	
	public UserDetailedView getDetails(Long id) {
		var user = findUserbyId(id);
		var details = detailsRepository.findById(id).orElse(null);
		
		var userDetailed = new UserDetailedView();
		
		if(details != null) {
			userDetailed.setName(user.getName());
			userDetailed.setEmail(user.getEmail());
			userDetailed.setCpf(user.getCpf());
			userDetailed.setAddress(details.getAdress());	
			userDetailed.setPhoneNumber(details.getPhoneNumber());
			userDetailed.setKnowledge(details.getKnowledge());
			userDetailed.setPreviousWorks(details.getPreviousWorks());
			userDetailed.setSkills(details.getSkills());
			userDetailed.setObjective(details.getObjective());
			userDetailed.setAbout(details.getAbout());
		}
		
		return userDetailed;
	}
	
	public UserDetails saveDetails(UserDetailedForm form) {
		
		var user = findUserbyId(form.getUserId());
		
		UserDetails details = null;
		
		if(user != null) {
			details = UserDetails.builder()
					.id(form.getUserId())
					.userId(form.getUserId())
					.adress(form.getAddress())
					.phoneNumber(form.getPhoneNumber())
					.knowledge(form.getKnowledge())
					.previousWorks(form.getPreviousWorks())
					.skills(form.getSkills())
					.about(form.getAbout())
					.objective(form.getObjective())
					.build();
		}
		
		return detailsRepository.save(details);
	}
}
