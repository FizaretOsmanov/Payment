package com.code.controllers;

import com.code.dto.response.transaction.TransactionResponse;
import com.code.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {


	private final TransactionService transactionService;

	@GetMapping("/transaction_history/{transactionId}")
	public ResponseEntity<List<TransactionResponse>> findAll() {
		return ResponseEntity.ok(transactionService.findAll());
	}

}
