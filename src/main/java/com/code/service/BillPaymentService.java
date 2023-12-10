package com.code.service;

import com.code.dto.request.billPayment.BillPaymentRequest;
import com.code.dto.response.billPayment.BillPaymentResponse;
import com.code.model.BillPayment;

import java.util.List;
import java.util.Set;

public interface BillPaymentService {

	BillPaymentResponse makeBillPayment(Long customerId,BillPaymentRequest billPayment);

	BillPaymentResponse viewBillPayments(Long billId, BillPaymentRequest paymentRequest);
}
