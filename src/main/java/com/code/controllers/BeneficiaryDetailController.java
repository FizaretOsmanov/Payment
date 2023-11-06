package com.code.controllers;

import com.code.exception.BeneficiaryDetailException;
import com.code.model.BeneficiaryDetail;
import com.code.service.BeneficiaryDetailServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ben")
public class BeneficiaryDetailController {

	private final BeneficiaryDetailServices beneficiaryService;
	
	@PostMapping("/{id}")
	public ResponseEntity<BeneficiaryDetail> addBeneficiaryDetail(@PathVariable("id") String uuid,
																  @RequestBody BeneficiaryDetail beneficiaryDetail)
			throws BeneficiaryDetailException {
		BeneficiaryDetail saved = beneficiaryService.addBeneficiary(uuid,beneficiaryDetail);
		return new ResponseEntity<BeneficiaryDetail>(saved,HttpStatus.CREATED);
	}
	@PatchMapping("/del")
	public ResponseEntity<BeneficiaryDetail> deleteBeneficiaryDetail(@RequestParam String uuid,
																	 @RequestParam String beneficiaryMobileNo)
			throws BeneficiaryDetailException {
		BeneficiaryDetail deleted = beneficiaryService.deleteBeneficiary(uuid,beneficiaryMobileNo);
		return new ResponseEntity<BeneficiaryDetail>(deleted,HttpStatus.OK);
	}
	@GetMapping("/{beneficiaryMobileNo}")
	public ResponseEntity<List<BeneficiaryDetail>> findBeneficiaryDetailByMobNo(@PathVariable("beneficiaryMobileNo") String MobNo)
			throws BeneficiaryDetailException {
		List<BeneficiaryDetail> beneficiaryDetail = beneficiaryService.viewBeneficiaryByMobileNo(MobNo);
		return new ResponseEntity<List<BeneficiaryDetail>>(beneficiaryDetail,HttpStatus.OK);
	}
	@GetMapping("/all")
	public ResponseEntity<List<BeneficiaryDetail>> findBeneficiaryDetailByCustomer(@RequestParam String uuid)
			throws BeneficiaryDetailException {
		List<BeneficiaryDetail> list = beneficiaryService.viewAllBeneficiary(uuid);
		return new ResponseEntity<List<BeneficiaryDetail>>(list,HttpStatus.OK);
	}
}
