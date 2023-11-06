package com.code.repository;

import com.code.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountDao extends JpaRepository<BankAccount, Integer>{

	BankAccount findByWalletId(Integer walletId);
    
}
