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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

	private final CustomerRepository customerRepository;

	private final LogInRepository logInRepository;

	private final ModelMapper modelMapper;

	@Override
	public LoginResponse logInAccount(LoginRequest loginData) {
		Optional<Customer> customer = customerRepository.findByUserName(loginData.getUsername());

		if (customer.isPresent()) {
			throw new ApplicationException(Errors.USER_EXISTS);
		}

		Customer newSignUp = customer.get();

		System.out.println(newSignUp);

		System.out.println(loginData.getMobileNo());
		Long newSignUpId = newSignUp.getUserId();
		Optional<Customer> currentSessionUser = customerRepository.findByUserId(newSignUpId);

		if (currentSessionUser.isPresent()) {
			throw new ApplicationException(Errors.USER_EXISTS);
		}
		if ((newSignUp.getMobileNo().equals(loginData.getMobileNo()))
				&& newSignUp.getPassword().equals(loginData.getPassword())) {
			LogIn savedUser = LogIn.builder()
					.mobileNo(loginData.getMobileNo())
					.password(loginData.getPassword())
					.build();
			logInRepository.save(savedUser);

			return LoginResponse.builder()
					.message(String.format("User registered successfully : &s", savedUser.getMobileNo()))
					.build();
		} else {
			throw new ApplicationException(Errors.PASSWORD_DID_NOT_MATCH);
		}

	}
}
