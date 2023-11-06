package com.code.model;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Data
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "beneficiary")
public class BeneficiaryDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer beneficiaryId;

	@Getter
	private String beneficiaryName;

	@Getter
	private String beneficiaryMobileNo;
	
	@Getter
	private Integer walletId;

}
