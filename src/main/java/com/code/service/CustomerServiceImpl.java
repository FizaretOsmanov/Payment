package com.code.service;

import com.code.dto.request.customer.CustomerRequest;
import com.code.dto.response.customer.CustomerResponse;
import com.code.errors.ApplicationException;
import com.code.errors.Errors;
import com.code.model.Customer;
import com.code.model.Wallet;
import com.code.repository.CustomerRepository;
import com.code.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	private final WalletRepository walletRepository;

	private final ModelMapper modelMapper;

	@Override
	public CustomerResponse createNewSignUp(CustomerRequest newSignUp) {
		System.out.println(newSignUp.toString());
			Optional<Customer> customer = customerRepository.findByUserName(newSignUp.getUserName());
		if (customer.isPresent()) {
			throw new ApplicationException(Errors.USER_EXISTS);
		}

		return modelMapper.map(newSignUp, CustomerResponse.class);
	}

	@Override
	public CustomerResponse updateSignUpDetails(Long customerId, CustomerRequest signUp) {
		customerRepository.findById(customerId)
				.orElseThrow(()-> new ApplicationException(Errors.USER_NOT_FOUND));

		Customer customerForUpdate = modelMapper.map(signUp, Customer.class);
		customerForUpdate.setPassword(signUp.getPassword());
		customerForUpdate.setUserName(signUp.getUserName());
		customerForUpdate.setUserId(customerId);

		Customer savedCustomer = customerRepository.save(customerForUpdate);
		return modelMapper.map(savedCustomer, CustomerResponse.class);
	}
}
