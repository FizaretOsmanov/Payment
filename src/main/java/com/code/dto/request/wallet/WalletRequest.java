package com.code.dto.request.wallet;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WalletRequest {

     String walletName;

     Long accountNumber;

     @JsonFormat(pattern = "MM/yyyy")
     LocalDateTime endTime;

     Integer cvv;

     Double balance;

}
