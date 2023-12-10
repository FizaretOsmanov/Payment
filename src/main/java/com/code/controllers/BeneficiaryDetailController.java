package com.code.controllers;

import com.code.dto.request.beneficiary.BeneficiaryRequest;
import com.code.dto.response.beneficiary.BeneficiaryResponse;
import com.code.service.BeneficiaryDetailServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/beneficiaries")
public class BeneficiaryDetailController {

	private final BeneficiaryDetailServices beneficiaryService;

	@PostMapping("/post")
	public ResponseEntity<BeneficiaryResponse> addBeneficiaryDetail(@RequestBody BeneficiaryRequest beneficiaryRequest) {
		return ResponseEntity.ok(beneficiaryService.addBeneficiary(beneficiaryRequest));
	}

	@PatchMapping("/{beneficiaryId})")
	public ResponseEntity<BeneficiaryResponse> deleteBeneficiaryDetail(@PathVariable Long beneficiaryId) {
		return ResponseEntity.ok(beneficiaryService.deleteBeneficiary(beneficiaryId));
	}
	@GetMapping("/{beneficiaryMobileNo}")
	public ResponseEntity<List<BeneficiaryResponse>> findBeneficiaryDetailByMobNo(@PathVariable("beneficiaryMobileNo")
																				  String MobNo) {
		return ResponseEntity.ok(beneficiaryService.viewBeneficiaryByMobileNo(MobNo));
	}

	@GetMapping("/all")
	public ResponseEntity<List<BeneficiaryResponse>> findAll() {
		return ResponseEntity.ok(beneficiaryService.findAll());
	}

}
