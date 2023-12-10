package com.code.repository;

import com.code.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long>{


	Optional<BankAccount> findByBankName(String bankName);

	BankAccount findByWalletId(Long walletId);
}
