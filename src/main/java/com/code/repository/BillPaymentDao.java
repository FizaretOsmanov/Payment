package com.code.repository;

import com.code.model.BillPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BillPaymentDao extends JpaRepository<BillPayment, Integer>{

	Set<BillPayment> findByWalletId(Integer walletId);
}
