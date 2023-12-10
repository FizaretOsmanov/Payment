package com.code.controllers;


import com.code.dto.request.customer.CustomerRequest;
import com.code.dto.response.customer.CustomerResponse;
import com.code.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class SignUpController {

	private final CustomerService signUpService;
	
	@PostMapping("/signUp")
	public ResponseEntity<CustomerResponse> createNewSignUpHandler(@RequestBody CustomerRequest newSignUp) {
		return ResponseEntity.ok(signUpService.createNewSignUp(newSignUp));

	}

	@PutMapping("/{customerId}/signUp/update")
	public ResponseEntity<CustomerResponse> updateSignUpDetailsHandler(@PathVariable Long customerId,
																	   @RequestBody CustomerRequest signUp) {
		return ResponseEntity.ok(signUpService.updateSignUpDetails(customerId, signUp));
	}
}
