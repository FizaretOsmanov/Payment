package com.code.service;

import com.code.exception.*;
import com.code.model.*;
import com.code.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

	private final TransactionDao transactionDao;

	private final BankAccountDao bankaccountdao;

	private final WalletDao walletDao;

	private final CustomerDAO customerDAO;

	private final SessionDAO currentSessionDAO;

	@Override
	public Double showBalance(String uniqueId) throws CustomerNotException, LoginException {
		
		Optional<CurrentSessionUser> currentSessionOptional = currentSessionDAO.findByUuid(uniqueId);
		
		if(currentSessionOptional.isEmpty()) {
			throw new LoginException("You need to login first");
		}

		Optional<Customer> customer =  customerDAO.findById(currentSessionOptional.get().getUserId());
		Wallet wallet =  customer.get().getWallet();
        return wallet.getBalance();
	}




	@Override
	public Transaction fundTransfer(String sourceMobileNo, String targetMobileNo, Double amount,String uniqueId)
			throws CustomerNotException, LoginException,BeneficiaryDetailException, InsufficientBalanceException {
		
		Optional<CurrentSessionUser> currentUser = currentSessionDAO.findByUuid(uniqueId);
		if(!currentUser.isPresent()) {
			throw new LoginException("You need to login first");
		}
		Optional<Customer> customerUser = customerDAO.findByMobileNo(sourceMobileNo);
		Customer customer = customerUser.get();
		Wallet wallet = customer.getWallet();


		boolean flag = true;
		List<BeneficiaryDetail> beneficiaryDetails = wallet.getBeneficiaryDetails();
		
		if(beneficiaryDetails==null || beneficiaryDetails.isEmpty()) {
			throw new BeneficiaryDetailException("You need to add beneficiary to you wallet");
		}
		
		for(BeneficiaryDetail bd:beneficiaryDetails) {
			System.out.println(bd);
			if (bd.getBeneficiaryMobileNo().equals(targetMobileNo)) {
				flag = false;
				break;
			}
		}
		
		Optional<Customer> target = customerDAO.findByMobileNo(targetMobileNo);
		System.out.println(target);
		
		
		if(flag) {
			throw new CustomerNotException("beneficiary is not add to wallet list");
		}
		
		if(!target.isPresent()) {
			System.out.println(target);
			throw new CustomerNotException("Number is not linked with the Database");
		}
		Customer tragetCustomer = target.get();
		
		if(wallet.getBalance()<amount) {
			throw new InsufficientBalanceException("Insufficient balance");
		}
		
		wallet.setBalance(wallet.getBalance()-amount);
		if(tragetCustomer.getWallet().getBalance()==null ) {
			tragetCustomer.getWallet().setBalance(amount);
		}else {
			tragetCustomer.getWallet().setBalance(tragetCustomer.getWallet().getBalance()+amount);
		}
		
		// Add to transaction
		
		Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.WALLET_TO_WALLET);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAmount(amount);
        transaction.setDescription("Fund Transfer from Wallet to Wallet Successful !");
        transaction.setWalletId(wallet.getWalletId());
        wallet.getTransaction().add(transaction);
        transactionDao.save(transaction);
		
		
		return transaction;
	}

	@Override
	public Transaction depositAmount(String uniqueId, Double amount) throws CustomerNotException,
			LoginException, InsufficientBalanceException {
		
		Optional<CurrentSessionUser> currentUser = currentSessionDAO.findByUuid(uniqueId);
		
		if(!currentUser.isPresent()) {
			throw new LoginException("You need to login first");
		}
		
		Optional<Customer> customerUser = customerDAO.findById(currentUser.get().getUserId());
		
		Customer customer = customerUser.get();

		Wallet wallet = customer.getWallet();

		BankAccount bankAccount = bankaccountdao.findByWalletId(wallet.getWalletId());
		
				
		if(bankAccount==null) {
			throw new NotAnyBankAddedYet("Bank Account not added to the wallet yet");
		}
		
		if(wallet.getBalance()<amount) {
			throw new InsufficientBalanceException("Insufficient balance");
		}
		
		customer.getWallet().setBalance(wallet.getBalance()-amount);
		
		bankAccount.setBankBalance(bankAccount.getBankBalance()+amount);
		
		bankaccountdao.save(bankAccount);
		walletDao.save(wallet);
		
		
		// Transaction Details
		Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.WALLET_TO_BANK);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAmount(amount);
        transaction.setDescription("Fund Transfer from Wallet to Bank is successful!");
        transaction.setWalletId(wallet.getWalletId());
        wallet.getTransaction().add(transaction);
        transactionDao.save(transaction);
        return transaction;
	}

	@Override
	public List<BeneficiaryDetail> getList(String uniqueId)
			throws CustomerNotException, LoginException, BeneficiaryDetailException {
		
			Optional<CurrentSessionUser> currentUser = currentSessionDAO.findByUuid(uniqueId);
			System.out.println(currentUser.get());
			
			if(!currentUser.isPresent()) {
				throw new LoginException("You need to login first");
			}
			
			Optional<Customer> custOptional = customerDAO.findById(currentUser.get().getUserId());
			Customer currUser = custOptional.get();
			Wallet wallet = currUser.getWallet();
			
			List<BeneficiaryDetail> beneficiaryDetails = wallet.getBeneficiaryDetails();
			
			if(beneficiaryDetails==null) {
				throw new BeneficiaryDetailException("You need to add beneficiary to your wallet");
			}
		
			return wallet.getBeneficiaryDetails();
		
	}

	
	public Customer addMoney(String uniqueId, Double amount)
			throws LoginException, CustomerNotException, InsufficientBalanceException {

		Optional<CurrentSessionUser> currOptional = currentSessionDAO.findByUuid(uniqueId);
		
		if(!currOptional.isPresent()) {
			throw new LoginException("You need to login first");
		}
		
		Optional<Customer> customer = customerDAO.findById(currOptional.get().getUserId());
		Customer  currcustomer = customer.get();
		Wallet wallet = currcustomer.getWallet();
		BankAccount bankAcc = bankaccountdao.findByWalletId(wallet.getWalletId());

		if(bankAcc==null) {
		    throw new CustomerNotException("Bank not linked");
		}
		
		if(bankAcc.getBankBalance()==0 || bankAcc.getBankBalance()<amount) {
		    throw new InsufficientBalanceException("Insufficient balance in bank");
		}
		
		bankAcc.setBankBalance(bankAcc.getBankBalance()-amount);
		
		wallet.setBalance(wallet.getBalance()+amount);
		
		bankaccountdao.save(bankAcc);
		walletDao.save(wallet);
		customerDAO.save(currcustomer);	
		
		Transaction transaction = new Transaction();

        transaction.setTransactionType(TransactionType.BANK_TO_WALLET);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAmount(amount);

        transaction.setDescription("Fund Transfer from Bank to Wallet is successful!");
        transaction.setWalletId(wallet.getWalletId());
        
        wallet.getTransaction().add(transaction);
        transactionDao.save(transaction);
        
        return currcustomer;
		
		
	}

	
	

}
