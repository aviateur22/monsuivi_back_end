package com.ctoutweb.monsuivi.infra.dto.response;

import com.ctoutweb.monsuivi.infra.dto.response.response.IResponseMessage;

import java.util.List;

public record LoginResponseDto(String jwt, long id, List<String> roles, String responseMessage) implements IResponseMessage {
    @Override
    public String getResponseMessage() {
        return responseMessage;
    }
}
