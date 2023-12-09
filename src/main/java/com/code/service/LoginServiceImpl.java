package com.code.service;

import com.code.dto.request.LoginRequest;
import com.code.dto.response.LoginResponse;
import com.code.exception.LoginException;
import com.code.model.CurrentSessionUser;
import com.code.model.Customer;
import com.code.model.LogIn;
import com.code.repository.CustomerDAO;
import com.code.repository.LogInDAO;
import com.code.repository.SessionDAO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

	private final CustomerDAO signUpDAO;

	private final SessionDAO SessionDAO;

	private final CurrentUserSessionService getCurrentLoginUserSession;

	private final LogInDAO loginDAO;

	private final ModelMapper modelMapper;

	@Override
	public LoginResponse logInAccount(LoginRequest loginData) throws LoginException {
		Optional<Customer> options = signUpDAO.findByMobileNo(loginData.getMobileNo());

		if(options.isEmpty()) {
			throw new LoginException("Invalid mobile Number ");
		}

		Customer newSignUp = options.get();

		System.out.println(newSignUp);

		System.out.println(loginData.getMobileNo());
		Integer newSignUpId = newSignUp.getUserId();
		Optional<CurrentSessionUser> currentSessionUser = SessionDAO.findByUserId(newSignUpId);

		if(currentSessionUser.isPresent()) {
			throw new LoginException("User already login with this user id");
		}
		if ((newSignUp.getMobileNo().equals(loginData.getMobileNo()))
				&& newSignUp.getPassword().equals(loginData.getPassword())) {
			LogIn savedUser = LogIn.builder()
					.mobileNo(loginData.getMobileNo())
					.password(loginData.getPassword())
					.build();
			loginDAO.save(savedUser);

			return LoginResponse.builder()
					.message(String.format("User registered successfully : &s", savedUser.getMobileNo()))
					.build();
		}else {
			throw new LoginException("Invalid mobile and password");
		}

	}

	@Override
	public String logOutFromAccount(String key) throws LoginException {
		Optional<CurrentSessionUser> currentSessionuserOptional = SessionDAO.findByUuid(key);
		
		if(currentSessionuserOptional.isEmpty()) {
			throw new LoginException("User has not logged in with this user id");
		}
		
		CurrentSessionUser currentSessionUser =getCurrentLoginUserSession.getCurrentUserSession(key);
		
		SessionDAO.delete(currentSessionUser);
		
		Optional<LogIn> loginData = loginDAO.findById(currentSessionuserOptional.get().getUserId());
		
		loginDAO.delete(loginData.get());
		
		return "Logged Out Successfully....";
	}

}
