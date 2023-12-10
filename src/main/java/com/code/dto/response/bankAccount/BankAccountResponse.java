package com.code.dto.response.bankAccount;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankAccountResponse {

    Long accountNumber;

    String mobileNumber;

    String bankName;

    Double bankBalance;

    String message;

}
