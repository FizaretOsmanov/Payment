package com.code.controllers;

import com.code.exception.BeneficiaryDetailException;
import com.code.exception.CustomerNotException;
import com.code.exception.InsufficientBalanceException;
import com.code.exception.LoginException;
import com.code.model.BeneficiaryDetail;
import com.code.model.Customer;
import com.code.model.Transaction;
import com.code.service.WalletServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/wallet")
public class WalletController {

	private final WalletServiceImpl walletServiceImpl;

	
	@GetMapping("/{id}")
	public ResponseEntity<String> viewWalletBalance(@PathVariable("id") String uniqueId)
			throws CustomerNotException, LoginException {
		Double balance = walletServiceImpl.showBalance(uniqueId);
		return new ResponseEntity<>(("Wallet balance is : " + balance), HttpStatus.CREATED);
	}


	@PutMapping("/function/{sourceMobileNo}/{targetMobileNo}/{amount}/{uniqueId}")
	public ResponseEntity<Transaction> WalletTOWalletTransfer(@PathVariable("sourceMobileNo") String sourceMobileNo,
															  @PathVariable("targetMobileNo") String targetMobileNo,
															  @PathVariable("amount") Double amount,
															  @PathVariable("uniqueId") String uniqueId)
			throws CustomerNotException, LoginException, BeneficiaryDetailException, InsufficientBalanceException {
		Transaction transaction = walletServiceImpl.fundTransfer(sourceMobileNo, targetMobileNo, amount, uniqueId);
		return new ResponseEntity<>(transaction, HttpStatus.OK);
	}

	@PutMapping("/deposit/{id}/{amount}")
	public ResponseEntity<Transaction> depositAmountFromWalletToBank(@PathVariable("id") String uniqueId,
																	 @PathVariable("amount") Double amount)
			throws CustomerNotException, LoginException, InsufficientBalanceException {
		Transaction transaction = walletServiceImpl.depositAmount(uniqueId, amount);
		return new ResponseEntity<>(transaction, HttpStatus.OK);
	}


	@GetMapping("/getbenList/{unique}")
	public ResponseEntity<List<BeneficiaryDetail>> getAllBeneficiaryCustomerFromWallet(String uniqueId,
																					   @PathVariable String unique)
			throws CustomerNotException, LoginException, BeneficiaryDetailException {
		List<BeneficiaryDetail> beneficiaryDetails = walletServiceImpl.getList(uniqueId);
		return new ResponseEntity<>(beneficiaryDetails, HttpStatus.OK);
	}

	@PostMapping("/addMoney/{unique}/{amount}")
	public ResponseEntity<Customer> addMoneyFromBankToWallet(String uniqueId, Double amount) throws Exception {
		Customer customer = walletServiceImpl.addMoney(uniqueId, amount);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

}
