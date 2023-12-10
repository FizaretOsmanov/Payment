package com.code.dto.response.login;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginResponse {

    Long userId;

    String mobileNo;

    String password;

    String message;

}
