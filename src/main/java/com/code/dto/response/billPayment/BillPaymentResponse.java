package com.code.dto.response.billPayment;

import com.code.model.BillType;
import com.code.model.TransactionType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillPaymentResponse {

    private Long billId;

    private BillType billtype;

    private TransactionType transactionType;

    private Double amount;

    private LocalDateTime time;

    private Long walletId;
}