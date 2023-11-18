package com.code.service;

import com.code.exception.InsufficientBalanceException;
import com.code.exception.UserNotLogedinException;
import com.code.model.*;
import com.code.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BillPaymentServiceImpl implements BillPaymentService {

	private final BillPaymentDao billDao;

	private final SessionDAO sessionDao;

	private final CustomerDAO cDao;

	private final WalletDao walletDao;

	private final TransactionDao transactionDao;

	@Override
	public BillPayment makeBillPayment(BillPayment billpayment, String uniqueId)
			throws InsufficientBalanceException, UserNotLogedinException {
		Optional<CurrentSessionUser> currentUser = sessionDao.findByUuid(uniqueId);

		if (!currentUser.isPresent()) {
			throw new UserNotLogedinException("Please Login first");
		}

		Optional<Customer> customer = cDao.findById(currentUser.get().getUserId());
		Wallet wallet = customer.get().getWallet();

		if (wallet.getBalance() < billpayment.getAmount()) {
			throw new InsufficientBalanceException("Insufficient balance in wallet, Add money to your wallet");
		}

		wallet.setBalance(wallet.getBalance() - billpayment.getAmount());
		walletDao.save(wallet);

		billpayment.setWalletId(wallet.getWalletId());
		billpayment.setTime(LocalDateTime.now());

		BillPayment completedPayment = billDao.save(billpayment);

		if (completedPayment != null) {
			Transaction transaction = new Transaction();
			transaction.setDescription(billpayment.getBilltype() + " successful");
			transaction.setAmount(billpayment.getAmount());
			transaction.setTransactionDate(LocalDateTime.now());
			transaction.setTransactionType(billpayment.getTransactionType());
			transaction.setWalletId(wallet.getWalletId());
			wallet.getTransaction().add(transaction);
			transactionDao.save(transaction);
		}
		System.out.println(billpayment);
		return completedPayment;
	}

	@Override
	public Set<BillPayment> viewBillPayments(String uniqueId) throws UserNotLogedinException {

		Optional<CurrentSessionUser> currentUser = sessionDao.findByUuid(uniqueId);

		if (!currentUser.isPresent()) {
			throw new UserNotLogedinException("Please Login first");
		}

		Optional<Customer> customer = cDao.findById(currentUser.get().getUserId());
		Wallet wallet = customer.get().getWallet();

		Set<BillPayment> billPayments = billDao.findByWalletId(wallet.getWalletId());
		return billPayments;
	}

}
