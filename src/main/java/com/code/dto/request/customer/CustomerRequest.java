package com.code.dto.request.customer;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequest {

     String userName;

     String email;

     String mobileNo;

     String password;
}
