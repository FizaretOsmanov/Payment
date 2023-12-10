package com.code.service;

import com.code.dto.response.transaction.TransactionResponse;
import com.code.errors.ApplicationException;
import com.code.errors.Errors;
import com.code.model.Customer;
import com.code.model.Transaction;
import com.code.model.TransactionType;
import com.code.model.Wallet;
import com.code.repository.CustomerRepository;
import com.code.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

	private final TransactionRepository transactionRepository;

	private final ModelMapper modelMapper;


	@Override
	public List<TransactionResponse> findAll() {
		return transactionRepository
				.findAll()
				.stream()
				.map(transaction -> modelMapper.map(transaction, TransactionResponse.class))
				.collect(Collectors.toList());

	}
}
