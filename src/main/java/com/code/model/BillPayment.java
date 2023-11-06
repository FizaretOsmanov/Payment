package com.code.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "billPayment")
public class BillPayment {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer billId;

	private BillType billtype;

	private TransactionType transactionType;

	private Double amount;

	private LocalDateTime time;

	private Integer walletId;

}
