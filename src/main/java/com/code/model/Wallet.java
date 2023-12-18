package com.code.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String walletName;

	private Double balance;

	private Integer cvv;

	@JsonIgnore
	@JsonFormat(pattern = "MM/yyyy")
	private LocalDateTime endTime;

	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "wallet")
	@JsonIgnore
	private List<Transaction> transaction;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "wallet")
	@JsonIgnore
	private List<BillPayment> billPayments;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "wallet")
	@JsonIgnore
	private List<BeneficiaryDetail> beneficiaryDetails;

	public Long getWalletId() {
        return null;
    }
}
