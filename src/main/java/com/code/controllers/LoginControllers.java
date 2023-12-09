package com.code.controllers;


import com.code.dto.request.LoginRequest;
import com.code.dto.response.LoginResponse;
import com.code.exception.LoginException;
import com.code.model.LogIn;
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
	public ResponseEntity<LoginResponse> loginHandler(@RequestBody LoginRequest loginData) throws LoginException {
		return ResponseEntity.ok(loginService.logInAccount(loginData));
	}
	
	@PatchMapping("/logout")
	public ResponseEntity<String> logOutFromAccount(@RequestParam String key) throws LoginException{
		String logout = loginService.logOutFromAccount(key);
		return new ResponseEntity<>(logout, HttpStatus.OK);
	}

}
