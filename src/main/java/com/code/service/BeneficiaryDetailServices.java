package com.code.service;

import com.code.exception.BeneficiaryDetailException;
import com.code.model.BeneficiaryDetail;

import java.util.List;

public interface BeneficiaryDetailServices {
	BeneficiaryDetail addBeneficiary(String uniqueId, BeneficiaryDetail beneficiaryDetail)
			throws BeneficiaryDetailException;

	BeneficiaryDetail deleteBeneficiary(String uniqueId, String beneficiaryMobileNo)
			throws BeneficiaryDetailException;

	List<BeneficiaryDetail> viewBeneficiaryByMobileNo(String beneficiaryMobileNo)
			throws BeneficiaryDetailException;

	List<BeneficiaryDetail> viewAllBeneficiary(String uniqueId) throws BeneficiaryDetailException;
}
