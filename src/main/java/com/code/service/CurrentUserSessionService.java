package com.code.service;

import com.code.exception.LoginException;
import com.code.model.CurrentSessionUser;
import com.code.model.Customer;

public interface CurrentUserSessionService {
	 CurrentSessionUser getCurrentUserSession(String key) throws LoginException;;

	 Integer getCurrentUserSessionId(String key) throws LoginException;;

	 Customer getSignUpDetails(String key) throws LoginException;;
}
