package com.code.dto.request.beneficiary;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BeneficiaryRequest {

    String beneficiaryName;

    String beneficiaryMobileNo;

}
