package com.code.service;

import com.code.dto.response.transaction.TransactionResponse;

import java.util.List;

public interface TransactionService {

	List<TransactionResponse> findAll();

}
