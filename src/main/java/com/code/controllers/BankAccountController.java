package com.code.controllers;

import com.code.dto.request.bankAccount.BankAccountRequest;
import com.code.dto.response.bankAccount.BankAccountResponse;
import com.code.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/banks")
public class BankAccountController {

	private final BankAccountService bankService;


	@PostMapping("/{walletId}")
	public ResponseEntity<BankAccountResponse> addBank(@RequestBody BankAccountRequest accountRequest) {
		return ResponseEntity.ok(bankService.addBank(accountRequest));
	}


	@GetMapping("/find")
	public ResponseEntity<List<BankAccountResponse>> findAll() {
		return ResponseEntity.ok(bankService.findAll());
	}

	@GetMapping("/{bankId}")
	public ResponseEntity<BankAccountResponse> findById(@PathVariable Long bankId) {
		return ResponseEntity.ok(bankService.findById(bankId));
	}

	@PutMapping("/{bankId}")
	public ResponseEntity<BankAccountResponse> update(@PathVariable Long bankId,
													  @RequestBody BankAccountRequest accountRequest) {
		return ResponseEntity.ok(bankService.update(bankId, accountRequest));
	}

	@DeleteMapping("/{bankId}")
	public ResponseEntity<BankAccountResponse> delete(@PathVariable Long bankId) {
		return ResponseEntity.ok(bankService.delete(bankId));
	}

}
