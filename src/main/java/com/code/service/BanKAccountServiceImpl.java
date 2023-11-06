package com.code.service;

import com.code.exception.BankAccountNotExists;
import com.code.exception.BankAlreadyAdded;
import com.code.exception.NotAnyBankAddedYet;
import com.code.exception.UserNotLogedinException;
import com.code.model.BankAccount;
import com.code.model.CurrentSessionUser;
import com.code.model.Customer;
import com.code.model.Wallet;
import com.code.repository.BankAccountDao;
import com.code.repository.CustomerDAO;
import com.code.repository.LogInDAO;
import com.code.repository.SessionDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BanKAccountServiceImpl implements BankAccountService {

	private final BankAccountDao bankDao;

	private final SessionDAO sessionDao;

	private final CustomerDAO cDao;

	private final LogInDAO logInDAO;

	@Override
	public BankAccount addBank(BankAccount bankAccount, String uniqueId)
			throws UserNotLogedinException, BankAlreadyAdded {

		Optional<CurrentSessionUser> currentUser = sessionDao.findByUuid(uniqueId);

		if (!currentUser.isPresent()) {
			throw new UserNotLogedinException("Please Login first");
		}

		Optional<Customer> customer = cDao.findById(currentUser.get().getUserId());
		Wallet wallet = customer.get().getWallet();

		Optional<BankAccount> bankAc = bankDao.findById(bankAccount.getAccountNumber());

		if (bankAc.isPresent()) {
			throw new BankAlreadyAdded(
					"Bank with " + bankAccount.getAccountNumber() + " this Account Nuber Already Exist");
		}

		System.out.println(wallet.getWalletId());
		bankAccount.setWalletId(wallet.getWalletId());
		return bankDao.save(bankAccount);

	}

	@Override
	public BankAccount removeBank(Integer accountNumber, String uniqueId)
			throws BankAccountNotExists, UserNotLogedinException {
		Optional<CurrentSessionUser> currentUser = sessionDao.findByUuid(uniqueId);

		if (!currentUser.isPresent()) {
			throw new UserNotLogedinException("Please Login first");
		}

		Optional<BankAccount> bankAccount = bankDao.findById(accountNumber);

		bankDao.delete(bankAccount.get());

		return bankAccount.get();

	}

	@Override
	public BankAccount viewBankAccountI(Integer accountNumber, String uniqueId)
			throws BankAccountNotExists, UserNotLogedinException {

		Optional<CurrentSessionUser> currentUser = sessionDao.findByUuid(uniqueId);

		if (!currentUser.isPresent()) {
			throw new UserNotLogedinException("Please Login first");
		}

		Optional<BankAccount> bankAccount = bankDao.findById(accountNumber);

		if (bankAccount.isPresent()) {
			return bankAccount.get();
		} else {
			throw new BankAccountNotExists(
					"Bank account is not existed with current account Number :" + accountNumber);
		}

	}

	@Override
	public BankAccount viewAllAccount(String uniqueId)
			throws UserNotLogedinException, NotAnyBankAddedYet, BankAccountNotExists {
		Optional<CurrentSessionUser> currentUser = sessionDao.findByUuid(uniqueId);

		if (!currentUser.isPresent()) {
			throw new UserNotLogedinException("Please Login first");
		}

		Optional<Customer> customer = cDao.findById(currentUser.get().getUserId());
		Wallet wallet = customer.get().getWallet();

		BankAccount bankAccounts = bankDao.findByWalletId(wallet.getWalletId());

		if (bankAccounts != null) {
			return bankAccounts;
		} else {
			throw new BankAccountNotExists("Bank account is not existed in current user ");
		}

	}

}
