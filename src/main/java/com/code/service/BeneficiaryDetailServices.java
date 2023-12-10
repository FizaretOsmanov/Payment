package com.code.service;

import com.code.dto.request.beneficiary.BeneficiaryRequest;
import com.code.dto.response.beneficiary.BeneficiaryResponse;

import java.util.List;

public interface BeneficiaryDetailServices {
	BeneficiaryResponse addBeneficiary(BeneficiaryRequest beneficiaryRequest);

	BeneficiaryResponse deleteBeneficiary(Long beneficiaryId);


	List<BeneficiaryResponse> viewBeneficiaryByMobileNo(String beneficiaryMobileNo);

	List<BeneficiaryResponse> findAll();
}
