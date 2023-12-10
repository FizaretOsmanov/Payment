package com.code.service;

import com.code.dto.request.customer.CustomerRequest;
import com.code.dto.response.customer.CustomerResponse;
import com.code.model.Customer;

public interface CustomerService {

	CustomerResponse createNewSignUp(CustomerRequest signUp);

	CustomerResponse updateSignUpDetails(Long customerId,CustomerRequest signUp);
}
