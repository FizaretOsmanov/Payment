package com.code.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class BillPayment {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long billId;

	private BillType billtype;

	private TransactionType transactionType;

	private Double amount;

	private LocalDateTime time;

	@ManyToOne
	@JsonIgnore
	@ToString.Exclude
	private Wallet wallet;

}
