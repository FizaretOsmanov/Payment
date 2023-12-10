package com.code.repository;

import com.code.dto.response.beneficiary.BeneficiaryResponse;
import com.code.model.BeneficiaryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeneficiaryDetailRepository extends JpaRepository<BeneficiaryDetail, Long> {

	List<BeneficiaryResponse> findByBeneficiaryMobileNo(String beneficiaryMobileNo);

	Optional<BeneficiaryDetail> findByBeneficiaryName(String beneficiaryName);
}
