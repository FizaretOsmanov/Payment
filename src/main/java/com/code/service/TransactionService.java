package com.code.service;

import com.code.exception.TransactionNotFoundException;
import com.code.exception.UserNotLogedinException;
import com.code.model.Transaction;
import com.code.model.TransactionType;

import java.util.List;

public interface TransactionService {

	List<Transaction> viewAllTransaction(String uniqueId) throws UserNotLogedinException, TransactionNotFoundException;

	List<Transaction> viewTransactionByDate(String from, String to, String uniqueId) throws UserNotLogedinException, TransactionNotFoundException;

	List<Transaction> viewAllTransactionByTransactionType(String uniqueId, TransactionType type) throws UserNotLogedinException, TransactionNotFoundException;


}
