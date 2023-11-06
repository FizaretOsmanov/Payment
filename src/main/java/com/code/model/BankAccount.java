package com.code.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class BankAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer accountNumber;

	private String mobileNumber;

	private String ifscCode;

	private String bankName;

	private double bankBalance;

	private Integer walletId;

}
