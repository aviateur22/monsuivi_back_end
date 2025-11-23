package com.ctoutweb.monsuivi.infra.dto.response.response;

public record ResponseMessageImpl(String responseMessage) implements IResponseMessage {
    @Override
    public String getResponseMessage() {
        return responseMessage;
    }
}
