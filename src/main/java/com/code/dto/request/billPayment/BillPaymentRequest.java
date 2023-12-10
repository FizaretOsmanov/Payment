package com.code.dto.request.billPayment;

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
public class BillPaymentRequest {

    private BillType billtype;

    private TransactionType transactionType;

    private Double amount;

    private LocalDateTime time;

    public void setWalletId(Long walletId) {
    }
}
