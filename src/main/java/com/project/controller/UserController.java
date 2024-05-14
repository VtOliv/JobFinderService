package com.project.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.domains.User;
import com.project.domains.form.LoginForm;
import com.project.domains.form.UserForm;
import com.project.domains.view.UserView;
import com.project.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

	private final UserService service;

	@PostMapping("/user/create")
	private ResponseEntity<User> createUser(@RequestBody UserForm form) {

		log.info("form={}", form);

		var createdUser = service.create(form);

		log.info("method={} id={}", "createUser", createdUser.getId());

		return ResponseEntity.status(CREATED).body(createdUser);
	}

	@PutMapping("/user/update/{id}")
	private ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserForm form) {

		log.info("form={}", form);

		var updatedUser = service.update(form, id);

		log.info("method={} id={}", "updateUser", updatedUser.getId());

		return ResponseEntity.status(OK).body(updatedUser);
	}

	@PostMapping("/user/login")
	private ResponseEntity<UserView> userLogin(@RequestBody LoginForm form) {
		log.info("form={}", form);

		var loginStatus = service.loginUser(form);

		log.info("method={} result={}", "userLogin", loginStatus.getResult());

		return ResponseEntity.status(OK).body(loginStatus);
	}

	@GetMapping("/user/{id}")
	private ResponseEntity<User> findUserById(@PathVariable Long id) {

		log.info("id={} method={}", id, "findOpportunityById");

		var response = service.findUserbyId(id);

		log.info("id={} name={}", id, response.getName());

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
