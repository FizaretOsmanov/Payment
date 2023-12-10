package com.code.controllers;

import com.code.dto.request.wallet.WalletRequest;
import com.code.dto.response.transaction.TransactionResponse;
import com.code.dto.response.wallet.WalletResponse;
import com.code.model.Customer;
import com.code.model.Wallet;
import com.code.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/wallet")
public class WalletController {

	private final WalletService walletService;


	@PostMapping("/save")
	public ResponseEntity<WalletResponse> save(@RequestBody WalletRequest walletRequest){
		return ResponseEntity.ok(walletService.create(walletRequest));
	}



	@GetMapping("/{walletId}")
	public ResponseEntity<Double> viewWalletBalance(@PathVariable Long walletId) {
		return ResponseEntity.ok(walletService.showBalance(walletId));
	}


	@PutMapping("/function/{sourceMobileNo}/{targetMobileNo}/{amount}")
	public ResponseEntity<TransactionResponse> WalletTOWalletTransfer(@PathVariable("sourceMobileNo") String sourceMobileNo,
																	  @PathVariable("targetMobileNo") String targetMobileNo,
																	  @PathVariable("amount") Double amount) {
		return ResponseEntity.ok(walletService.fundTransfer(sourceMobileNo, targetMobileNo, amount));
	}

	@PutMapping("/deposit/{walletId}/{bankId}/{amount}")
	public ResponseEntity<TransactionResponse> depositAmountFromWalletToBank(@PathVariable Long walletId,
																			 @PathVariable Long bankId,
																			 @PathVariable("amount") Double amount) {
		return ResponseEntity.ok(walletService.depositAmount(walletId, bankId, amount));
	}

	@PostMapping("/addMoney/{bankId}/{walletId}/{amount}")
	public ResponseEntity<TransactionResponse> addMoneyFromBankToWallet(Long bankId, Long walletId,Double amount) {
		return ResponseEntity.ok(walletService.addMoney(bankId, walletId, amount));
	}

	@DeleteMapping("/{walletId}")
	public ResponseEntity<WalletResponse> delete(@PathVariable Long walletId){
		return ResponseEntity.ok(walletService.delete(walletId));
	}

}
