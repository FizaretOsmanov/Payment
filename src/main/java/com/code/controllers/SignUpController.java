package com.code.controllers;


import com.code.dto.request.customer.CustomerRequest;
import com.code.dto.response.customer.CustomerResponse;
import com.code.model.Customer;
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
	public ResponseEntity<?> createNewSignUpHandler(@RequestBody Customer customer) {
		return ResponseEntity.ok(signUpService.createNewSignUp(customer));

	}

	@PutMapping("/{mobileNo}/signUp/update")
	public ResponseEntity<CustomerResponse> updateSignUpDetailsHandler(@PathVariable String mobileNo,
																	   @RequestBody CustomerRequest signUp) {
		return ResponseEntity.ok(signUpService.updateSignUpDetails(mobileNo, signUp));
	}

	@RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> confirmAccount(@RequestParam("token") String confirmationToken) {
		return signUpService.confirmUserEmail(confirmationToken);
	}
}




