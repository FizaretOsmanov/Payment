package com.code.service;

import com.code.exception.LoginException;
import com.code.model.Customer;

public interface CustomerService {

	Customer createNewSignUp(Customer signUp) throws LoginException;

	Customer updateSignUpDetails(Customer signUp, String key) throws LoginException;
}
