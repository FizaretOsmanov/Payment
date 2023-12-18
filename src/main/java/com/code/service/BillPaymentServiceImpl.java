package com.code.service;

import com.code.dto.request.billPayment.BillPaymentRequest;
import com.code.dto.response.billPayment.BillPaymentResponse;
import com.code.errors.ApplicationException;
import com.code.errors.Errors;
import com.code.model.BillPayment;
import com.code.model.Customer;
import com.code.model.Transaction;
import com.code.model.Wallet;
import com.code.repository.BillPaymentRepository;
import com.code.repository.CustomerRepository;
import com.code.repository.TransactionRepository;
import com.code.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillPaymentServiceImpl implements BillPaymentService {

	private final BillPaymentRepository billPaymentRepository;

	private final CustomerRepository customerRepository;

	private final WalletRepository walletRepository;

	private final TransactionRepository transactionRepository;

	private final ModelMapper modelMapper;

	@Override
	public BillPaymentResponse makeBillPayment(Long customerId, BillPaymentRequest billPayment) {

		Optional<Customer> customer = customerRepository.findById(customerId);
		if (customer.isEmpty()) {
			throw new ApplicationException(Errors.USER_NOT_FOUND);
		}
		Wallet wallet = customer.get().getWallet();

		if (wallet.getBalance() < billPayment.getAmount()) {
			throw new RuntimeException("Insufficient balance in wallet, Add money to your wallet");
		}

		wallet.setBalance(wallet.getBalance() - billPayment.getAmount());
		walletRepository.save(wallet);

		billPayment.setWalletId(wallet.getWalletId());
		billPayment.setTime(LocalDateTime.now());

		Transaction transaction = Transaction.builder()
				.description(billPayment.getBilltype() + "successfull")
				.amount(billPayment.getAmount())
				.transactionDate(billPayment.getTime())
				.transactionType(billPayment.getTransactionType())
				.build();
		transactionRepository.save(transaction);
		System.out.println(billPayment);
		return modelMapper.map(billPayment, BillPaymentResponse.class);
	}

	@Override
	public BillPaymentResponse viewBillPayments(Long billId, BillPaymentRequest paymentRequest) {

		BillPayment billPayment = billPaymentRepository.findById(billId)
				.orElseThrow(() -> new ApplicationException(Errors.BILL_NOT_FOUND));
		return modelMapper.map(billPayment, BillPaymentResponse.class);
	}

}
