package com.code.service;

import com.code.dto.request.wallet.WalletRequest;
import com.code.dto.response.transaction.TransactionResponse;
import com.code.dto.response.wallet.WalletResponse;
import com.code.errors.ApplicationException;
import com.code.errors.Errors;
import com.code.model.*;
import com.code.repository.BankAccountRepository;
import com.code.repository.CustomerRepository;
import com.code.repository.TransactionRepository;
import com.code.repository.WalletRepository;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

	private final TransactionRepository transactionRepository;

	private final BankAccountRepository bankAccountRepository;

	private final WalletRepository walletRepository;

	private final CustomerRepository customerRepository;

	private final ModelMapper modelMapper;


	@Override
	public Double showBalance(Long walletId) {

		Optional<Wallet> wallet = walletRepository.findById(walletId);

		if (wallet.isEmpty()) {
			throw new ApplicationException(Errors.WALLET_NOT_FOUND);
		}

		Wallet wallet1 = wallet.get();
		return wallet1.getBalance();
	}




	@Override
	public TransactionResponse fundTransfer(String sourceMobileNo, String targetMobileNo, Double amount) {

		Optional<Customer> customerUser = customerRepository.findByMobileNo(sourceMobileNo);
		Customer customer = customerUser.get();
		Wallet wallet = customer.getWallet();


		boolean flag = true;
		List<BeneficiaryDetail> beneficiaryDetails = wallet.getBeneficiaryDetails();
		
		if(beneficiaryDetails==null || beneficiaryDetails.isEmpty()) {
			throw new RuntimeException("You need to add beneficiary to you wallet");
		}
		
		for(BeneficiaryDetail bd:beneficiaryDetails) {
			System.out.println(bd);
			if (bd.getBeneficiaryMobileNo().equals(targetMobileNo)) {
				flag = false;
				break;
			}
		}

		Optional<Customer> target = customerRepository.findByMobileNo(targetMobileNo);
		System.out.println(target);
		
		
		if(flag) {
			throw new RuntimeException("beneficiary is not add to wallet list");
		}
		
		if(!target.isPresent()) {
			System.out.println(target);
			throw new RuntimeException("Number is not linked with the Database");
		}
		Customer tragetCustomer = target.get();
		
		if(wallet.getBalance()<amount) {
			throw new RuntimeException("Insufficient balance");
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
		transaction.setDescription("Transfer from Wallet to Wallet Successful !");
        transaction.setWalletId(wallet.getWalletId());
        wallet.getTransaction().add(transaction);
		transactionRepository.save(transaction);


		return modelMapper.map(transaction, TransactionResponse.class);
	}

	@Override
	public TransactionResponse depositAmount(Long walletId, Long bankId, Double amount) {

		Wallet wallet = walletRepository.findById(walletId)
				.orElseThrow(() -> new ApplicationException(Errors.WALLET_NOT_FOUND));

		BankAccount bankAccount = bankAccountRepository.findById(bankId)
				.orElseThrow(() -> new ApplicationException(Errors.BANK_NOT_FOUND));


		if(wallet.getBalance()<amount) {
			throw new RuntimeException("Insufficient balance");
		}

		wallet.setBalance(wallet.getBalance() - amount);
		
		bankAccount.setBankBalance(bankAccount.getBankBalance()+amount);

		bankAccountRepository.save(bankAccount);
		walletRepository.save(wallet);
		
		
		// Transaction Details
		Transaction transaction1 = new Transaction();
		transaction1.setTransactionType(TransactionType.WALLET_TO_BANK);
		transaction1.setTransactionDate(LocalDateTime.now());
		transaction1.setAmount(amount);
		transaction1.setDescription("Fund Transfer from Wallet to Bank is successful!");
		transaction1.setWalletId(wallet.getWalletId());
		wallet.getTransaction().add(transaction1);
		transactionRepository.save(transaction1);
		return modelMapper.map(transaction1, TransactionResponse.class);
	}

	public TransactionResponse addMoney(Long bankId, Long walletId, Double amount) {

		BankAccount bankAccount = bankAccountRepository.findById(bankId)
				.orElseThrow(() -> new ApplicationException(Errors.BANK_NOT_FOUND));

		Wallet wallet = walletRepository.findById(walletId)
				.orElseThrow(() -> new ApplicationException(Errors.WALLET_NOT_FOUND));

		if (bankAccount == null) {
			throw new RuntimeException("Bank not linked");
		}

		if (bankAccount.getBankBalance() == 0 || bankAccount.getBankBalance() < amount) {
			throw new RuntimeException("Insufficient balance in bank");
		}

		bankAccount.setBankBalance(bankAccount.getBankBalance() - amount);
		
		wallet.setBalance(wallet.getBalance()+amount);

		bankAccountRepository.save(bankAccount);
		walletRepository.save(wallet);
		
		Transaction transaction = new Transaction();

        transaction.setTransactionType(TransactionType.BANK_TO_WALLET);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAmount(amount);

        transaction.setDescription("Fund Transfer from Bank to Wallet is successful!");
        transaction.setWalletId(wallet.getWalletId());
        
        wallet.getTransaction().add(transaction);
		transactionRepository.save(transaction);

		return modelMapper.map(transaction, TransactionResponse.class);
		
		
	}

	@Override
	public WalletResponse delete(Long walletId) {
		Wallet wallet = walletRepository.findById(walletId)
				.orElseThrow(() -> new ApplicationException(Errors.WALLET_NOT_FOUND));

		walletRepository.delete(wallet);
		return modelMapper.map(wallet, WalletResponse.class);
	}

	@Override
	public WalletResponse create(WalletRequest walletRequest) {

		Optional<Wallet> wallet = walletRepository.findByWalletName(walletRequest.getWalletName());
		if(wallet.isEmpty()){
			throw new ApplicationException(Errors.WALLET_NOT_FOUND);
		}

		Wallet walletForSave = Wallet.builder()
				.balance(walletRequest.getBalance())
				.walletName(walletRequest.getWalletName())
				.build();

		Wallet savedWallet = walletRepository.save(walletForSave);
		return modelMapper.map(savedWallet, WalletResponse.class);
	}

}
