package com.code.dto.response.login;

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
public class LoginResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;

    String mobileNo;

    String password;

    String message;

}
