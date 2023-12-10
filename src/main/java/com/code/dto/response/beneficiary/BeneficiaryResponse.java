package com.code.dto.response.beneficiary;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BeneficiaryResponse {

    Long beneficiaryId;

    String beneficiaryName;

    String beneficiaryMobileNo;

    Long walletId;
}
