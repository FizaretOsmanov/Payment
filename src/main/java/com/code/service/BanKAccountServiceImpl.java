package com.code.service;

import com.code.dto.request.bankAccount.BankAccountRequest;
import com.code.dto.response.bankAccount.BankAccountResponse;
import com.code.errors.ApplicationException;
import com.code.errors.Errors;
import com.code.model.BankAccount;
import com.code.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BanKAccountServiceImpl implements BankAccountService {

	private final BankAccountRepository accountRepository; //there are beans for wiring and calls method

	private final ModelMapper modelMapper;

	@Override
	public BankAccountResponse addBank(BankAccountRequest accountRequest) {
		Optional<BankAccount> bankAccount = accountRepository.findByBankName(accountRequest.getBankName());

		if (bankAccount.isEmpty()) {
			throw new ApplicationException(Errors.BANK_NOT_FOUND);
		}

		BankAccount bankForSave = BankAccount.builder()
				.walletId(accountRequest.getWalletId())
				.bankBalance(accountRequest.getBankBalance())
				.bankName(accountRequest.getBankName())
				.mobileNumber(accountRequest.getMobileNumber())
				.build();

		BankAccount savedBank = accountRepository.save(bankForSave);
		return modelMapper.map(savedBank, BankAccountResponse.class);

	}

	@Override
	public List<BankAccountResponse> findAll() {
		return accountRepository
				.findAll()
				.stream()
				.map(bankAccount -> modelMapper.map(bankAccount, BankAccountResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	public BankAccountResponse findById(Long bankId) {
		BankAccount bankAccount = accountRepository.findById(bankId).orElseThrow(
				() -> new ApplicationException(Errors.BANK_NOT_FOUND));
		return modelMapper.map(bankAccount, BankAccountResponse.class);
	}

	@Override
	public BankAccountResponse update(Long bankId, BankAccountRequest accountRequest) {
		BankAccount bankAccount = accountRepository.findById(bankId).orElseThrow(
				() -> new ApplicationException(Errors.BANK_NOT_FOUND));

		BankAccount bankForUpdate = BankAccount.builder()
				.bankId(bankAccount.getBankId())
				.walletId(bankAccount.getWalletId())
				.bankBalance(bankAccount.getBankBalance())
				.bankName(bankAccount.getBankName())
				.build();

		BankAccount savedBank = accountRepository.save(bankForUpdate);
		return modelMapper.map(savedBank, BankAccountResponse.class);
	}

	@Override
	public BankAccountResponse delete(Long bankId) {
		BankAccount bankAccount = accountRepository.findById(bankId).orElseThrow(
				() -> new ApplicationException(Errors.BANK_NOT_FOUND));

		accountRepository.delete(bankAccount);
		return modelMapper.map(bankAccount, BankAccountResponse.class);
	}
}
