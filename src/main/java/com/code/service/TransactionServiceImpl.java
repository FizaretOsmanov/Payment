package com.code.service;

import com.code.exception.TransactionNotFoundException;
import com.code.exception.UserNotLogedinException;
import com.code.model.*;
import com.code.repository.CustomerDAO;
import com.code.repository.SessionDAO;
import com.code.repository.TransactionDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

	private final CustomerDAO customerDAO;

	private final TransactionDao transactionDao;

	private final SessionDAO sessionDao;

	@Override
	public List<Transaction> viewAllTransaction(String uniqueId)
			throws UserNotLogedinException, TransactionNotFoundException {
		
		Optional<CurrentSessionUser> optional = sessionDao.findByUuid(uniqueId);
		
		if(!optional.isPresent()) {
			throw new UserNotLogedinException("User is not Logged in");
		}
		
		Optional<Customer> customer=  customerDAO.findById(optional.get().getUserId());
		
		
		Wallet wallet = customer.get().getWallet();
		
		List<Transaction> transactions  = wallet.getTransaction();

		if (!transactions.isEmpty()) {
			return transactions;
		}else {
			throw new TransactionNotFoundException("Not found any transaction with wallet");
		}
		
	}

	@Override
	public List<Transaction> viewTransactionByDate(String from, String to, String uniqueId)
			throws UserNotLogedinException, TransactionNotFoundException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		LocalDate start = LocalDate.parse(from, formatter);
		LocalDate end = LocalDate.parse(to, formatter);
		
		Optional<CurrentSessionUser> optional = sessionDao.findByUuid(uniqueId);
		
		if(!optional.isPresent()) {
			throw new UserNotLogedinException("User not logged in");
		}
		
		Optional<Customer> customer=  customerDAO.findById(optional.get().getUserId());
		
		Wallet wallet = customer.get().getWallet();
		
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		List<Transaction> transaction = transactionDao.findByWalletId(wallet.getWalletId());
		List<Transaction> trans = new ArrayList<>();
		
		for(int i = 0 ; i<transaction.size() ; i++) {
			String str1 =  transaction.get(i).getTransactionDate().format(formatter2);
			LocalDate temp = LocalDate.parse(str1, formatter);
			if ((temp.isAfter(start) && temp.isBefore(end)) || temp.equals(start) || temp.equals(end)) {
				trans.add(transaction.get(i));
			}
		}
		return trans;

	}


	@Override
	public List<Transaction> viewAllTransactionByTransactionType(String uniqueId, TransactionType type)
			throws UserNotLogedinException, TransactionNotFoundException {

		Optional<CurrentSessionUser> optional = sessionDao.findByUuid(uniqueId);
		
		if(!optional.isPresent()) {
			throw new UserNotLogedinException("User not logged In");
		}
		
		Optional<Customer> customer=  customerDAO.findById(optional.get().getUserId());
		Wallet wallet = customer.get().getWallet();
		List<Transaction> transaction = wallet.getTransaction();
		List<Transaction> transactionslist = transactionDao.getTransactionByTransactionType(type);
		
		List<Transaction> transactionLists= new ArrayList<>();
		for(Transaction tr: transactionslist) {
			if (tr.getWalletId().equals(wallet.getWalletId())) {
				transactionLists.add(tr);
			}
		}

		if (!transactionLists.isEmpty()) {
			return transactionLists;
		}else {
			throw new TransactionNotFoundException("Transaction not found");
		}
	}



}
