package com.code.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Transaction {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;
    
    private TransactionType transactionType;
    
    @CreatedDate
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime transactionDate;
    
    private Double amount;
    
	private String description;
    
    private Long  walletId;

    @Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", transactionType=" + transactionType
				+ ", transactionDate=" + transactionDate + ", amount=" + amount + ", description=" + description
				+ ", walletId=" + walletId + "]";
	}
}
