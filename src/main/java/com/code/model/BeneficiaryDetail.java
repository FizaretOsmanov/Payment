package com.code.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "beneficiary")
public class BeneficiaryDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long beneficiaryId;

	@Getter
	private String beneficiaryName;

	@Getter
	@NotNull
	@Size(min = 10, max = 10)
	@Pattern(regexp = "[6-9][0-9]{9}", message = "Mobile number must have 10 digits mobile Number")
	private String beneficiaryMobileNo;
	
	@Getter
	private Long walletId;

}
