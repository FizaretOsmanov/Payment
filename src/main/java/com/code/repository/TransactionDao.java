package com.code.repository;

import com.code.model.Transaction;
import com.code.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Integer> {

	List<Transaction> findByWalletId(Integer walletId);

	List<Transaction> getTransactionByTransactionType(TransactionType type);

}
