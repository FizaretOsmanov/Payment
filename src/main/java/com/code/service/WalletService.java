package com.code.service;

import com.code.dto.request.wallet.WalletRequest;
import com.code.dto.response.beneficiary.BeneficiaryResponse;
import com.code.dto.response.transaction.TransactionResponse;
import com.code.dto.response.wallet.WalletResponse;
import com.code.model.BeneficiaryDetail;
import com.code.model.Customer;
import com.code.model.Transaction;

import java.util.List;

public interface WalletService {

	Double showBalance(Long walletId);

	TransactionResponse fundTransfer(String sourceMobileNo, String targetMobileNo, Double amount);

	TransactionResponse depositAmount(Long walletId,Long bankId,Double amount);
	
	TransactionResponse addMoney(Long bankId, Long walletId,Double amount);

	WalletResponse delete(Long walletId);

    WalletResponse create(WalletRequest walletRequest);
}
