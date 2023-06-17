package com.project.blockchainapi.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClientRequestException extends RuntimeException {

    private String internalMessage;
    private Throwable internalCause;

    public ClientRequestException(String internalMessage, Exception e) {
        this.internalCause = e;
        this.internalMessage = internalMessage;
    }

    public ClientRequestException(String internalMessage) {
        super(internalMessage);
        this.internalMessage = internalMessage;
    }

}
