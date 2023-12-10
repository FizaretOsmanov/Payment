package com.code.service;

import com.code.dto.request.login.LoginRequest;
import com.code.dto.response.login.LoginResponse;

public interface LoginService {

	LoginResponse logInAccount(LoginRequest loginData);

}
