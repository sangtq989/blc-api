package com.project.blockchainapi.response;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class MessageResponse{
    private String internalStatus;
    private String internalMessage;
    private Object data;
}
