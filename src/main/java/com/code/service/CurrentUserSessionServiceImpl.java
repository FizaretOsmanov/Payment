package com.code.service;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.code.exception.LoginException;
import com.code.model.CurrentSessionUser;
import com.code.model.Customer;
import com.code.repository.CustomerDAO;
import com.code.repository.SessionDAO;

@Service
@RequiredArgsConstructor
public class CurrentUserSessionServiceImpl implements CurrentUserSessionService {

	private final SessionDAO sessionDAO;

	private final CustomerDAO signUpDAO;

	@Override
	public CurrentSessionUser getCurrentUserSession(String key) throws LoginException {
		Optional<CurrentSessionUser> currentSessionUser = sessionDAO.findByUuid(key);

		if (currentSessionUser.isPresent()) {
			return currentSessionUser.get();
		} else {
			throw new LoginException("UnAuthorized!!!");
		}
	}

	@Override
	public Customer getSignUpDetails(String key)  {
		Optional<CurrentSessionUser> currentUser = sessionDAO.findByUuid(key);
		if (!currentUser.isPresent()) {
			return null;
		}
		Integer SignUpUserId = currentUser.get().getUserId();
		System.out.println(SignUpUserId);

		return (signUpDAO.findById(SignUpUserId)).get();
	}

}
