package com.code.controllers;

import com.code.dto.request.billPayment.BillPaymentRequest;
import com.code.dto.response.billPayment.BillPaymentResponse;
import com.code.service.BillPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/billPayment")
public class BillPaymentController {

	private final BillPaymentService billPaymentService;

	@PostMapping("/{customerId}/post")
	public ResponseEntity<BillPaymentResponse> addNewBillPaymentDetails(@PathVariable Long customerId,
																		@RequestBody BillPaymentRequest billPayment) {
		return ResponseEntity.ok(billPaymentService.makeBillPayment(customerId, billPayment));
	}

	@GetMapping("/{billId}")
	public ResponseEntity<BillPaymentResponse> viewAllBillPayments(@PathVariable Long billId,
																   @RequestBody BillPaymentRequest paymentRequest) {
		return ResponseEntity.ok(billPaymentService.viewBillPayments(billId, paymentRequest));
	}
	
}
