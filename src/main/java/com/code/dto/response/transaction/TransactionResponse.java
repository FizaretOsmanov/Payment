package com.code.dto.response.transaction;

import com.code.model.TransactionType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionResponse {

    Long transactionId;

    TransactionType transactionType;

    LocalDateTime transactionDate;

    Double amount;

    String description;

}
