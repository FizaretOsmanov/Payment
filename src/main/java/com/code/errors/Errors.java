package com.code.errors;

import org.springframework.http.HttpStatus;

public enum Errors implements ErrorResponse {

    jakarta_NOT_NULL("jakarta.validation.constraints.NotNull.message", HttpStatus.BAD_REQUEST,
            "The value can't be empty"),

    BANK_NOT_FOUND("BANK_NOT_FOUND", HttpStatus.NO_CONTENT, "Bank Not Found"),

    WALLET_NOT_FOUND("WALLET_NOT_FOUND", HttpStatus.NO_CONTENT, "Wallet Not Found"),

    USER_NOT_FOUND("USER_NOT_FOUND", HttpStatus.NO_CONTENT, "User Not Found"),

    BENEFICIARY_NOT_FOUND("BENEFICIARY_NOT_FOUND", HttpStatus.NO_CONTENT, "Beneficiary Not Found"),

    BILL_NOT_FOUND("BILL_NOT_FOUND", HttpStatus.NO_CONTENT, "Bill Not Found"),

    TRANSACTION_NOT_FOUND("TRANSACTION_NOT_FOUND", HttpStatus.NO_CONTENT, "Transaction Not Found"),

    PHONE_NUMBER_NOT_FOUND("PHONE_NUMBER_NOT_FOUND", HttpStatus.NO_CONTENT, "Phone Number Not Found"),

    USER_EXISTS("USER_EXISTS", HttpStatus.BAD_REQUEST, "User Is Already Exists"),

    PASSWORD_DID_NOT_MATCH("PASSWORD_DID_NOT_MATCH", HttpStatus.CONFLICT, "Password Did Not Match");

    final String key;

    final HttpStatus httpStatus;

    final String message;

    Errors(String key, HttpStatus httpStatus, String message) {
        this.message = message;
        this.key = key;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return null;
    }
}