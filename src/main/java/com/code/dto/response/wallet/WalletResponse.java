package com.code.dto.response.wallet;

import com.code.model.BeneficiaryDetail;
import com.code.model.BillPayment;
import com.code.model.Customer;
import com.code.model.Transaction;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WalletResponse {

    Long id;

    Double balance;

    Customer customer;

    List<Transaction> transaction;

    List<BillPayment> billPayments;

    List<BeneficiaryDetail> beneficiaryDetails;

    public Long getWalletId() {
        return null;
    }
}
