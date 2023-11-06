package com.code.controllers;

import com.code.exception.InsufficientBalanceException;
import com.code.exception.UserNotLogedinException;
import com.code.model.BillPayment;
import com.code.service.BillPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/billPayment")
public class BillPaymentController {

	private final BillPaymentService bService;
	
	@PostMapping("/{uniqueId}")
	public ResponseEntity<BillPayment> addNewBillPaymentDetails(@RequestBody BillPayment billPayment,
																@PathVariable String uniqueId)
			throws UserNotLogedinException, InsufficientBalanceException {
		BillPayment addBill =  bService.makeBillPayment(billPayment, uniqueId);
		return new ResponseEntity<BillPayment> (addBill, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Set<BillPayment>> viewAllBillPayments(@PathVariable("id") String uniqueId)
			throws UserNotLogedinException {
		Set<BillPayment> billPayments = bService.viewBillPayments(uniqueId);
		return new ResponseEntity<Set<BillPayment>>(billPayments,HttpStatus.ACCEPTED);
	}
	
}
