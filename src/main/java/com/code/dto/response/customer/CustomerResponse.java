package com.code.dto.response.customer;

import com.code.dto.response.wallet.WalletResponse;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;

    String userName;

    String email;

    String mobileNo;

    String password;

    WalletResponse wallet;
}
