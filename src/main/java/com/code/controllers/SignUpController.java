package com.code.controllers;


import com.code.exception.LoginException;
import com.code.model.Customer;
import com.code.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class SignUpController {

	private CustomerService signUpService;
	
	@PostMapping("/signUp")
	public ResponseEntity<Customer> createNewSignUpHandler(@RequestBody Customer newSignUp)
			throws LoginException {
		Customer newSignedUp =signUpService.createNewSignUp(newSignUp);
		return new ResponseEntity<Customer>(newSignedUp,HttpStatus.CREATED);

	}
	
	@PutMapping("/signUp/update")
	public ResponseEntity<Customer> updateSignUpDetailsHandler(@RequestBody Customer signUp,
															   @RequestParam String key)
			throws LoginException{
		Customer newUpdatedSignUp = signUpService.updateSignUpDetails(signUp,key);
		return new ResponseEntity<Customer>(newUpdatedSignUp,HttpStatus.ACCEPTED);
	}
}
