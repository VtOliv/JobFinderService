package com.project.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.domains.User;
import com.project.domains.form.LoginForm;
import com.project.domains.form.UserForm;
import com.project.domains.view.UserView;
import com.project.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final ModelMapper mapper;
	private final UserRepository repository;

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
				.name(user.getName())
				.role(user.getIsRecruiter() ? "Recrutador" : "Usuario")
				.result(result? "success" : "failure")
				.build();
	}
}
