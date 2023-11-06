package com.code.service;

import com.code.exception.BankAccountNotExists;
import com.code.exception.BankAlreadyAdded;
import com.code.exception.NotAnyBankAddedYet;
import com.code.exception.UserNotLogedinException;
import com.code.model.BankAccount;

public interface BankAccountService {
	
	 BankAccount addBank(BankAccount bankAccount,String uniqueId) throws UserNotLogedinException,BankAlreadyAdded;

	 BankAccount removeBank(Integer accountNumber,String uniqueId) throws BankAccountNotExists,UserNotLogedinException;
	
	 BankAccount viewBankAccountI(Integer accountNumber,String uniqueId) throws BankAccountNotExists,UserNotLogedinException ;
	
	 BankAccount viewAllAccount(String uniqueId) throws UserNotLogedinException,NotAnyBankAddedYet, BankAccountNotExists;
}
