package com.code.service;

import com.code.exception.LoginException;
import com.code.model.LogIn;

public interface LoginService {

	String logInAccount(LogIn loginData) throws LoginException;

	String logOutFromAccount(String key) throws LoginException;
}
