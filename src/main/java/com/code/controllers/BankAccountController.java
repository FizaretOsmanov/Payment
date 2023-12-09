package com.code.controllers;

import com.code.exception.BankAccountNotExists;
import com.code.exception.BankAlreadyAdded;
import com.code.exception.NotAnyBankAddedYet;
import com.code.exception.UserNotLogedinException;
import com.code.model.BankAccount;
import com.code.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/bank")
public class BankAccountController {

	private final BankAccountService bankService;

	@PostMapping("/{id}")
	public ResponseEntity<BankAccount> addBankAccountToWallet(@RequestBody BankAccount bankaccount, @PathVariable String id)
			throws BankAlreadyAdded, UserNotLogedinException {
		return new ResponseEntity<>(bankaccount, HttpStatus.ACCEPTED);
	}

	@PatchMapping("/{acc}/{id}") //patchMapping as like put mapping
	public ResponseEntity<BankAccount> deleteBankAccountFromWallet(@PathVariable("acc") Integer accountNumber,
			@PathVariable("id") String uniqueId) throws BankAccountNotExists, UserNotLogedinException {
		BankAccount accountDeleted = bankService.removeBank(accountNumber, uniqueId);
		return new ResponseEntity<>(accountDeleted, HttpStatus.ACCEPTED);
	}

	@GetMapping("/{acc}/{id}")
	public ResponseEntity<BankAccount> viewBankAccountDetails(@PathVariable("acc") Integer accountNumber,
			@PathVariable("id") String uniqueId) throws BankAccountNotExists, UserNotLogedinException {
		BankAccount accountDetails = bankService.viewBankAccountI(accountNumber, uniqueId);
		return new ResponseEntity<>(accountDetails, HttpStatus.ACCEPTED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BankAccount> viewAllAccountDetails(@PathVariable("id") String uniqueId)
			throws NotAnyBankAddedYet, UserNotLogedinException, BankAccountNotExists {
		BankAccount accountDetails = bankService.viewAllAccount(uniqueId);
		return new ResponseEntity<>(accountDetails, HttpStatus.ACCEPTED);
	}

}
