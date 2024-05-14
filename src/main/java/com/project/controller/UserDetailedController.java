package com.project.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.domains.UserDetails;
import com.project.domains.form.UserDetailedForm;
import com.project.domains.view.UserDetailedView;
import com.project.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserDetailedController {

	private final UserService service;

	@GetMapping("user/complete/{id}")
	private ResponseEntity<UserDetailedView> getUserDetailedById(@PathVariable Long id) {

		log.info("id={} method={}", id, "getUserDetailedById");

		var response = service.getDetails(id);

		log.info("id={} name={}", id, response.getName());

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping("/userDetailed")
	private ResponseEntity<UserDetails> completeAccount(@RequestBody UserDetailedForm form) {
		log.info("userId={}", form.getUserId());

		var details = service.saveDetails(form);

		log.info("method={} result={}", "completeAccount", details.getUserId());

		return ResponseEntity.status(OK).body(details);
	}
}
