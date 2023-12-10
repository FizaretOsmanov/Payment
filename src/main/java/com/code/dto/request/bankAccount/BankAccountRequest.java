package com.code.dto.request.bankAccount;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankAccountRequest {

    String mobileNumber;

    String bankName;

    Double bankBalance;


    public Long getWalletId() {
        return null;
    }
}
