package com.code.repository;

import com.code.exception.BeneficiaryDetailException;
import com.code.model.BeneficiaryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeneficiaryDetailDao extends JpaRepository<BeneficiaryDetail, Integer>{

	List<BeneficiaryDetail> findByBeneficiaryMobileNo(String beneficiaryMobileNo) throws BeneficiaryDetailException;

	List<BeneficiaryDetail> findByWalletId(Integer walletId) throws BeneficiaryDetailException;

}
