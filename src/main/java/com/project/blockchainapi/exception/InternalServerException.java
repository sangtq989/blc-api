package com.project.blockchainapi.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InternalServerException extends RuntimeException{

    private final String internalMessage;
    private final Throwable internalCause;

    public InternalServerException(String internalMessage, Exception e) {
        this.internalCause = e;
        this.internalMessage= internalMessage;
    }
}
