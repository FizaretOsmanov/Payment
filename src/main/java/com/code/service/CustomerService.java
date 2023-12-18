package com.code.service;

import com.code.dto.request.customer.CustomerRequest;
import com.code.dto.response.customer.CustomerResponse;
import com.code.model.Customer;
import org.springframework.http.ResponseEntity;

public interface CustomerService {

	ResponseEntity<?> createNewSignUp(Customer customer);

	CustomerResponse updateSignUpDetails(String mobileNo,CustomerRequest signUp);

	ResponseEntity<?> confirmUserEmail(String confirmationToken);
}
