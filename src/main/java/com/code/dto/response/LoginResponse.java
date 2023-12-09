package com.code.dto.response;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class LoginResponse {

    private String mobileNo;

    private String password;

    private String message;

}
