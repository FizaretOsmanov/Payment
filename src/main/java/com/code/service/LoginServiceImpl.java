package com.code.service;

import com.code.dto.request.login.LoginRequest;
import com.code.dto.response.login.LoginResponse;
import com.code.errors.ApplicationException;
import com.code.errors.Errors;
import com.code.model.Customer;
import com.code.model.LogIn;
import com.code.repository.CustomerRepository;
import com.code.repository.LogInRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

	private final CustomerRepository customerRepository;

	private final LogInRepository logInRepository;

	@Override
	public LoginResponse logInAccount(LoginRequest loginData) {
		// Find the customer by mobile number
		Optional<Customer> customer = customerRepository.findByMobileNo(loginData.getMobileNo());

		// If customer not found, throw an exception
		if (customer.isEmpty()) {
			throw new ApplicationException(Errors.USER_NOT_FOUND);
		}

		// Retrieve the customer from Optional
		Customer existingCustomer = customer.get();

		// Print for debugging (optional)
		System.out.println(existingCustomer);

		// Get the user ID from the existing customer
		Long existingCustomerId = existingCustomer.getUserId();

		// Check if a session already exists for the user
		Optional<Customer> currentSessionUser = customerRepository.findByUserId(existingCustomerId);

		// If a session already exists, throw an exception
		if (currentSessionUser.isPresent()) {
			throw new ApplicationException(Errors.USER_EXISTS);
		}

		// Check if the provided password matches the stored password
		if (existingCustomer.getPassword().equals(loginData.getPassword())) {
			// Save the login details
			LogIn savedUser = LogIn.builder()
					.mobileNo(loginData.getMobileNo())
					.password(loginData.getPassword())
					.build();

			logInRepository.save(savedUser);

			// Return a success message
			return LoginResponse.builder()
					.message(String.format("User logged in successfully: %s", savedUser.getMobileNo()))
					.build();
		} else {
			// If the passwords don't match, throw an exception
			throw new ApplicationException(Errors.PASSWORD_DID_NOT_MATCH);
		}
	}
}
