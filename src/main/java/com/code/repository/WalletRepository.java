package com.code.repository;

import com.code.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long>{

    Optional<Wallet> findByWalletName(String walletName);
}
