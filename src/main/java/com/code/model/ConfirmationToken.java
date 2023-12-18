package com.code.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tokens")
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    Long id;

    @Column(name = "confirmation_token")
    String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    Date createDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer userEntity;

    public ConfirmationToken(Customer user) {
        this.confirmationToken = generateConfirmationToken();
        this.userEntity = user;
    }

    public String generateConfirmationToken() {
        return UUID.randomUUID().toString();
    }
}

