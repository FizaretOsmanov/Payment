package com.code.service;

import com.code.dto.request.LoginRequest;
import com.code.dto.response.LoginResponse;
import com.code.exception.LoginException;
import com.code.model.LogIn;

public interface LoginService {

	LoginResponse logInAccount(LoginRequest loginData) throws LoginException;

	String logOutFromAccount(String key) throws LoginException;
}
