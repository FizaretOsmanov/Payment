package com.code.service;

import com.code.exception.BeneficiaryDetailException;
import com.code.exception.CustomerNotException;
import com.code.exception.InsufficientBalanceException;
import com.code.exception.LoginException;
import com.code.model.BeneficiaryDetail;
import com.code.model.Customer;
import com.code.model.Transaction;

import javax.naming.InsufficientResourcesException;
import java.util.List;

public interface WalletService {

	Double showBalance(String mobileNo) throws CustomerNotException, LoginException;

	Transaction fundTransfer(String sourceMobileNo, String targetMobileNo, Double amount, String uniqueId)
			throws CustomerNotException, BeneficiaryDetailException, LoginException, InsufficientBalanceException;

	Transaction depositAmount(String uniqueId, Double amount)
			throws CustomerNotException, LoginException, InsufficientResourcesException, InsufficientBalanceException;

	List<BeneficiaryDetail> getList(String uniqueId)
			throws CustomerNotException, LoginException, BeneficiaryDetailException;

	Customer addMoney(String uniqueId, Double amount) throws Exception;
	
}
