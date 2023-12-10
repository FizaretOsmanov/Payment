package com.code.controllers;


import com.code.dto.request.login.LoginRequest;
import com.code.dto.response.login.LoginResponse;
import com.code.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class LoginControllers {

	private final LoginService loginService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> loginHandler(@RequestBody LoginRequest loginData) {
		return ResponseEntity.ok(loginService.logInAccount(loginData));
	}

}
