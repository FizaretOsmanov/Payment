package com.code.service;

import com.code.exception.InsufficientBalanceException;
import com.code.exception.UserNotLogedinException;
import com.code.model.BillPayment;

import java.util.Set;

public interface BillPaymentService {

	BillPayment makeBillPayment(BillPayment billpayment, String uniqueId)
			throws InsufficientBalanceException, UserNotLogedinException;

	Set<BillPayment> viewBillPayments(String uniqueId) throws UserNotLogedinException;
}
