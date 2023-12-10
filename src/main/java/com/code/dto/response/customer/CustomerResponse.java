package com.code.dto.response.customer;

import com.code.dto.response.wallet.WalletResponse;
import com.code.model.Wallet;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponse {

    Long userId;

    String userName;

    String email;

    String mobileNo;

    String password;

    WalletResponse walletResponse;

}
