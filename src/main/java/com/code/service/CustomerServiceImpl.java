package com.code.service;

import com.code.dto.request.customer.CustomerRequest;
import com.code.dto.response.customer.CustomerResponse;
import com.code.errors.ApplicationException;
import com.code.errors.Errors;
import com.code.mailSender.MailSender;
import com.code.model.ConfirmationToken;
import com.code.model.Customer;
import com.code.repository.CustomerRepository;
import com.code.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	private final TokenRepository tokenRepository;

	private final ModelMapper modelMapper;

	private final MailSender mailSender;

	@Override
	public ResponseEntity<?> createNewSignUp(Customer customer) {

		if (customerRepository.existsByEmail(customer.getEmail())) {
			return ResponseEntity.badRequest().body("Error: User already exists");
		}
		customerRepository.save(customer);
		ConfirmationToken confirmationToken = new ConfirmationToken();
		tokenRepository.save(confirmationToken);
		String subject = "Complete the registration";
		String text = "Hello, pls verify the email at the link:" +
				"http://localhost:8082/v1/registration/confirm-account?token=" + confirmationToken.getConfirmationToken();
		mailSender.sendEmail(customer.getEmail(), subject, text);
		System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());
		return ResponseEntity.ok("Təsdiqlə brat emailivi sənin də işin getsin bizim də");
	}

	@Override
	public CustomerResponse updateSignUpDetails(String mobileNo, CustomerRequest signUp) {
		customerRepository.findByMobileNo(mobileNo)
				.orElseThrow(()-> new ApplicationException(Errors.USER_NOT_FOUND));

		Customer customerForUpdate = modelMapper.map(signUp, Customer.class);
		customerForUpdate.setPassword(signUp.getPassword());
		customerForUpdate.setUserName(signUp.getUserName());

		Customer savedCustomer = customerRepository.save(customerForUpdate);
		return modelMapper.map(savedCustomer, CustomerResponse.class);
	}

	public ResponseEntity<?> confirmUserEmail(String confirmationToken) {

		ConfirmationToken token = tokenRepository.findByConfirmationToken(confirmationToken);

		if (token != null) {
			Customer user = customerRepository.findByEmailIgnoreCase(token.getUserEntity().getEmail());
			user.setEnabled(true);
			customerRepository.save(user);
			return ResponseEntity.ok("Completed with successfully");
		}
		return ResponseEntity.badRequest().body("Did not completed");
	}
}

