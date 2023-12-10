package com.code.service;

import com.code.dto.request.beneficiary.BeneficiaryRequest;
import com.code.dto.response.beneficiary.BeneficiaryResponse;
import com.code.errors.ApplicationException;
import com.code.errors.Errors;
import com.code.model.BeneficiaryDetail;
import com.code.repository.BeneficiaryDetailRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeneficiaryDetailServicesImpl implements BeneficiaryDetailServices {

	private final BeneficiaryDetailRepository beneficiaryDetailRepository;

	private final ModelMapper modelMapper;
	

	@Override
	public BeneficiaryResponse addBeneficiary(BeneficiaryRequest beneficiaryRequest) {
		Optional<BeneficiaryDetail> beneficiaryDetail = beneficiaryDetailRepository
				.findByBeneficiaryName(beneficiaryRequest.getBeneficiaryName());
		if (beneficiaryDetail.isEmpty()) {
			throw new ApplicationException(Errors.BENEFICIARY_NOT_FOUND);
		}

		BeneficiaryDetail beneficiaryForSave = BeneficiaryDetail.builder()
				.beneficiaryName(beneficiaryRequest.getBeneficiaryName())
				.beneficiaryMobileNo(beneficiaryRequest.getBeneficiaryMobileNo())
				.build();
		BeneficiaryDetail savedBeneficiary = beneficiaryDetailRepository.save(beneficiaryForSave);
		return modelMapper.map(savedBeneficiary, BeneficiaryResponse.class);

	}

	@Override
	public BeneficiaryResponse deleteBeneficiary(Long beneficiaryId) {
		BeneficiaryDetail beneficiaryDetail = beneficiaryDetailRepository.findById(beneficiaryId).orElseThrow(() ->
				new ApplicationException(Errors.BENEFICIARY_NOT_FOUND));
		beneficiaryDetailRepository.delete(beneficiaryDetail);
		return modelMapper.map(beneficiaryDetail, BeneficiaryResponse.class);
	}

	@Override
	public List<BeneficiaryResponse> viewBeneficiaryByMobileNo(String beneficiaryMobileNo) {
		List<BeneficiaryResponse> beneficiaryDetail = beneficiaryDetailRepository
				.findByBeneficiaryMobileNo(beneficiaryMobileNo);
		if(beneficiaryDetail.isEmpty()) {
			throw new RuntimeException("No Beneficiary found with Mobile No : " + beneficiaryMobileNo);
		}else {
			return beneficiaryDetail;
		}
	}

	@Override
	public List<BeneficiaryResponse> findAll() {
		return beneficiaryDetailRepository
				.findAll()
				.stream()
				.map(beneficiaryDetail -> modelMapper.map(beneficiaryDetail, BeneficiaryResponse.class))
				.collect(Collectors.toList());
	}


}
