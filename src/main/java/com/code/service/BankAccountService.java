package com.code.service;

import com.code.dto.request.bankAccount.BankAccountRequest;
import com.code.dto.response.bankAccount.BankAccountResponse;

import java.util.List;

public interface BankAccountService {

	BankAccountResponse addBank(BankAccountRequest accountRequest);

	List<BankAccountResponse> findAll();

	BankAccountResponse findById(Long bankId);

	BankAccountResponse update(Long bankId, BankAccountRequest accountRequest);

	BankAccountResponse delete(Long bankId);
}
